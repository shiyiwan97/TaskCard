# todo customize text、button context、sound and communicate with java
$ContinueBtn = New-BTButton -Content 'Continue Task'
$DoneBtn = New-BTButton -Content 'Have Done Task'

$Splat = @{
    Text = 'Task Time''s Up'
    Sound = 'IM'
    Button = $ContinueBtn, $DoneBtn
}

New-BurntToastNotification @Splat