# MyLifeLogger

1. 세부 기능
 - 기존의 과제였던 MyLifeLogger의 기능을 토대로 추가적인 세부 기능으로는 사용자가 휴대폰을 떨어뜨리거나 넘어졌을 때 Android에 존재하는 센서들을 이용하여 휴대폰의 내부 데이터베이스에 기록하고 확인할 수 있게 할 것이다.

2. 필요한 기술(세부 기능을 추가하기 위하여)
 - Android에 기본적으로 탑재 되어있는 물리적 센서들 중에 휴대폰의 급격한 움직임을 감지하기 위해 가속도 센서와 회전 운동을 감지할 수 있는 자이로 센서를 다루는 기술이 필요하다.
import android.hardware.Sensor;  
import android.hardware.SensorEvent;  
import android.hardware.SensorEventListener;  
import android.hardware.SensorListener;  
import android.hardware.SensorManager;  

 - Android Studio를 이용하여 센서를 이용하기 위해 import 할 Class

 - 센서를 다루기 위해 필요한 메소드
 - getSystemService(Context.SENSOR_SERVICE)
 	: 센서 이용을 위해 센서 매니저 호출

 - getDefaultSensor(Sensor.TYPE_GYROSCOPE) 
	: 자이로스코프 센서 사용

 - getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
 	: 가속도 센서 사용
	두 가지 센서 모두 Integer 형 변수 3개씩( X, Y, Z ) 이용하여 값을 받음

 - public void onSensorChanged(SensorEvent event) ;
	( 센서값이 변화할 때 사용되는 메소드 )
	=> Sensor 객체를 하나 생성하여 자이로 센서가 인식될 때와 가속도 센서가 인식될 때를 조건문을 사용하여 구현
	(event.sensor 이용하고 event.values 이용하여 값을 받아옴)

3. UI
 - 공책 같은 배경에 어플리케이션 이름(MyLifeLogger)을 화면 위쪽에 위치 시키고 GPS 기록을 시작/중지하는 토글 버튼과 현재 위치에서의 기록을 남기는 버튼과 넘어짐을 감지하는 기능을 시작/중지하는 토글 버튼, 현재까지의 기록을 확인하는 버튼을 순서대로 넣어 UI 구현하고 기록이 있는 위치에서 Google Map에서 Marker을 이용하여 표시

4. 세부 일정
 - 1주차 : 자이로 센서와 가속도 센서의 이용법 숙지와 단순히 센서값 측정하는 어플리케이션 구현(2016.11.28 완료)
 - 2주차 : 1주차에 만들어진 어플리케이션을 MyLifeLogger에 적용시키고 조건문을 이용한 이벤트 발생하는 어플리케이션 구현
 - 3주차 : 2주차까지 완성된 어플리케이션에 UI 구현

5. 일지
 - 2016.11.28 기능 구현 위한 기본 레이아웃 구성
 - 2016.11.28 토글 버튼을 이용하여 Google Map API 불러옴 (굳이 토글버튼으로 할 필요는 없을 것 같음)
 - 2016.11.28 토글 버튼을 이용하여 센서 감지 화면으로 넘어가게 하고 자이로 센서값(X,Y,Z)과 가속도 센서값(X,Y,Z)을 읽어오는데 까지 성공
	: 핸드폰을 정지 시켜도 가속도 센서값이 0으로 나오지 않는데 중력 가속도 값을 빼지 않아서 인 것 같다.
	: 센서값들이 너무 자주 변화하여 특정 순간에 기록하기 어려운데 측정하는 주기를 길게 해야할 것 같다.
	: 램 4기가짜리 노트북으로 안드로이드 스튜디오를 이용하기엔 너무 무겁다...
