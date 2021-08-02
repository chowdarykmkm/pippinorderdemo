ControlFocus("Open","","Edit1")
Sleep(500)
If $CmdLine[0] = 1 Then
ControlSetText("Open","","Edit1",$CmdLine[1])
Else
ControlSetText("Open","","Edit1",$CmdLine[1]&' '&$CmdLine[2])
EndIf
Sleep(500)
ControlClick("Open","","Button1")