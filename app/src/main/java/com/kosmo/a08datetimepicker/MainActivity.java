package com.kosmo.a08datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //클래스 전체에서 사용 할 전역변수 생성 및 객체선언
    Calendar calendar; //캘린더 클래스(시간, 날짜 생성)
    TextView date_tv, time_tv; //텍스트뷰(시간, 날짜 표시)
    int yearStr, monthStr, dayStr; //현재날짜
    int hourStr, minuteStr, secondStr; //현재시간

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //텍스트뷰 위젯 가져오기
        date_tv = (TextView)findViewById(R.id.date_tv);
        time_tv = (TextView)findViewById(R.id.time_tv);

        //캘린더 클래스의 객체생성 : 생성자가 없는 클래스이므로 유틸리티 메소드를 통해 객체 생성 [getInstance()]
        calendar = Calendar.getInstance();

        yearStr = calendar.get(calendar.YEAR);
        monthStr = calendar.get(calendar.MONTH); //0부터 11까지 반환
        dayStr = calendar.get(calendar.DATE);
        hourStr = calendar.get(calendar.HOUR_OF_DAY);
        minuteStr = calendar.get(calendar.MINUTE);
        secondStr = calendar.get(calendar.SECOND);

        /*
        현재 날짜와 시간을 텍스트뷰에 설정함.(단, 월(달)의 경우에는
        +1을 해야 현재 월(달)이 된다.)
         */
        date_tv.setText(yearStr + "년" + (monthStr + 1) + "월" + dayStr + "일");
        time_tv.setText(hourStr + "시" + minuteStr + "분" + secondStr + "초");

        //날짜선택 버튼을 가져와서 리스너 부착
        Button btn_datepicker = (Button)findViewById(R.id.btn_datepicker);
        btn_datepicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                버튼을 눌렀을 때 아래 데이트피커 대화창을 띄워준다.
                사용법 :
                     new DatePickerDialog(
                        대화창을 띄울 컨텍스트, 리스너, 설정 할 년, 월, 일
                     );
                 ※ 리스너는 클래스 생성시 내부 혹은 외부에서 선언하는 두 가지 방법이 있다.
                 */
                DatePickerDialog dialog =
                        new DatePickerDialog(v.getContext(), listener, yearStr, monthStr, dayStr);
                dialog.show();
            }
        });

        //시간선택버튼에 리스너 부착
        Button btn_timepicker = (Button)findViewById(R.id.btn_timepicker);
        btn_timepicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                버튼 클래스 타임피커 대화창을 띄워준다.
                사용법 :
                    TimePickerDialog(
                        컨텍스트, 리스너, 설정할 시간, 분, 24시간 포맷
                    ).show();
                ※ 24시간 포맥 : true(24시간제로 표시)
                               false(12시간제로 표시)

                 */

                //타임피커에서 시간선택 후 확인버튼을 눌렀을 때의 리스너
                new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {

                    //매개변수로 전달되는 값은 사용자가 선택한 시간임
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //선택한 시간을 텍스트뷰에 설정함
                        time_tv.setText(String.format("설정된시간%n%d시 %d분", hourOfDay, minute));
                        //선택한 시간을 토스트로 띄워줌
                        Toast.makeText(getApplicationContext(),
                                String.format("설정된시간%n%d시 %d분", hourOfDay, minute),
                                Toast.LENGTH_LONG).show();
                    }
                },
                        hourStr, minuteStr, true).show();
            }
        });
    }////onCreate End

    //데이트피커에서 날짜 선택 후 확인버튼을 눌렀을 떄를 감지하는 리스너 선언언
    private  DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //선택한 날짜를 텍스트뷰에 설정
            date_tv.setText(String.format("설정된날자%n%d년 %d월 %d일", year, (month + 1), dayOfMonth));
            //토스트로 선택한 날짜 출력
            Toast.makeText(getApplicationContext(),
                    year + "년" + (month + 1) + "월" + dayOfMonth + "일",
                    Toast.LENGTH_LONG).show();
        }
    };
}












