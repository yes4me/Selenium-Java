rem ================================================================
rem 2015/06/22 Thomas batch file to javac JUnit file
rem "rem" are comments, "@" is to make sure nothing get displayed
rem ================================================================

@cls

@echo BEGIN: %Date%, %time%
@java -cp .;C:\JUNIT\junit-4.10.jar org.junit.runner.JUnitCore %1
@echo END: %date%, %time%