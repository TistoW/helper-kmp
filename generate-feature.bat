@echo off
setlocal enabledelayedexpansion

echo ================================================
echo   Feature Generator - Zenenta POS
echo ================================================
echo.

:: Input nama feature baru
set /p NEW_FEATURE="Enter new feature name (e.g., product, transaction): "

if "%NEW_FEATURE%"=="" (
    echo Error: Feature name cannot be empty!
    pause
    exit /b 1
)

:: Convert to lowercase using PowerShell
for /f "delims=" %%a in ('powershell -Command "('%NEW_FEATURE%').ToLower()"') do set "NEW_FEATURE=%%a"

:: Convert first letter to uppercase for class names
for /f "delims=" %%a in ('powershell -Command "('%NEW_FEATURE%').Substring(0,1).ToUpper() + ('%NEW_FEATURE%').Substring(1).ToLower()"') do set "CAPITALIZED=%%a"

set SOURCE_DIR=feature\example
set TARGET_DIR=feature\%NEW_FEATURE%

echo.
echo Source: %SOURCE_DIR%
echo Target: %TARGET_DIR%
echo Package: lowercase = %NEW_FEATURE%, Capitalized = %CAPITALIZED%
echo.

:: Check if target already exists
if exist "%TARGET_DIR%" (
    echo Warning: Feature "%NEW_FEATURE%" already exists!
    set /p CONFIRM="Do you want to overwrite? (y/n): "
    if /i not "!CONFIRM!"=="y" (
        echo Cancelled.
        pause
        exit /b 0
    )
    echo Deleting existing feature...
    rmdir /s /q "%TARGET_DIR%"
)

:: Copy folder structure (EXCLUDE build folder)
echo Copying files (excluding build folder)...
xcopy "%SOURCE_DIR%" "%TARGET_DIR%\" /E /I /Q /EXCLUDE:exclude-files.txt

:: Create exclude list dynamically if xcopy fails
if errorlevel 1 (
    echo Trying alternative copy method...
    robocopy "%SOURCE_DIR%" "%TARGET_DIR%" /E /XD build .gradle /XF *.class *.jar /NFL /NDL /NJH /NJS
    if errorlevel 8 (
        echo Error: Failed to copy files!
        pause
        exit /b 1
    )
)

echo Files copied successfully!
echo.
echo Replacing package names...

:: Replace package names in all .kt files
for /r "%TARGET_DIR%" %%f in (*.kt) do (
    echo Processing: %%~nxf

    :: Replace in one go using PowerShell
    powershell -Command "$content = Get-Content '%%f'; $content = $content -replace 'feature\.example', 'feature.%NEW_FEATURE%'; $content = $content -replace '\.example\.', '.%NEW_FEATURE%.'; $content = $content -replace 'Example', '%CAPITALIZED%'; Set-Content '%%f' $content"
)

:: Rename folders (example -> product)
echo Renaming folders...
for /f "delims=" %%d in ('dir /b /s /ad "%TARGET_DIR%\*example*" 2^>nul') do (
    set "OLD_PATH=%%d"
    set "NEW_PATH=!OLD_PATH:example=%NEW_FEATURE%!"
    if not "!OLD_PATH!"=="!NEW_PATH!" (
        if exist "!OLD_PATH!" (
            echo Renaming folder: %%~nxd
            move "!OLD_PATH!" "!NEW_PATH!" >nul 2>&1
        )
    )
)

:: Rename files (Example -> Product)
echo Renaming files...
for /f "delims=" %%f in ('dir /b /s /a-d "%TARGET_DIR%\*Example*" 2^>nul') do (
    set "OLD_FILE=%%f"
    set "NEW_FILE=!OLD_FILE:Example=%CAPITALIZED%!"
    if not "!OLD_FILE!"=="!NEW_FILE!" (
        if exist "!OLD_FILE!" (
            echo Renaming file: %%~nxf
            move "!OLD_FILE!" "!NEW_FILE!" >nul 2>&1
        )
    )
)

echo.
echo ================================================
echo   SUCCESS! Feature "%NEW_FEATURE%" created!
echo ================================================
echo.
echo Location: %TARGET_DIR%
echo.
echo Excluded: build/, .gradle/, *.class, *.jar
echo.
echo Package structure:
echo   - Lowercase: feature.%NEW_FEATURE%
echo   - Classes: %CAPITALIZED%FormScreen.kt, %CAPITALIZED%ViewModel.kt
echo.
echo Next steps:
echo 1. Add to settings.gradle.kts:
echo    include(":feature:%NEW_FEATURE%")
echo.
echo 2. Sync Gradle project
echo.
echo 3. Start developing!
echo.
pause