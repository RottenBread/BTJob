# BTJob
간단한 마인크래프트 직업 플러그인! <br>
당신의 서버에 직업을 추가해 보아요

# API
GetLevel.getFarmer(player) - player 의 농부 레벨을 불러옵니다. <br>
GetLevel.getFisher(player) - player 의 어부 레벨을 불러옵니다. <br>
GetLevel.getMiner(player) - player 의 광부 레벨을 불러옵니다. <br>
GetLevel.getHunter(player) - player 의 전투 레벨을 불러옵니다.

# Updates
v1.0 - 릴리즈 <br>
v1.1 - 경험치가 초과하였을때 레벨이 오르지 않는 오류 수정, 잘못 업로드된 파일 교체 <br>
v1.2 - 보스바 출력시 콘솔에 오류가 출력되던 버그 수정 <br>
v1.3 - 이제 레벨이 오를때 경험치가 0으로 되지 않고 다음 레벨에 수치가 반영됨,<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 레벨이 오른 후 보스바에 제대로 경험치가 반영되지 않는 오류 수정 <br>
v1.4 - 전투 레벨 추가

# How To Use (Skript)
```
import:
  org.rottenbread.bTJob.api.GetLevel

on load:
    set {GetLevel} to new GetLevel()

command /광부레벨:
    trigger:
        set {_level} to {GetLevel}.getMiner(player)
        broadcast "%{_level}%"
```
# License
본 저작물 이용자는 재판매 행위를 할 수 없음을 밝힙니다. <br>
Discord: jhmoon_aa <br>
