#include <Servo.h>

#define wrist_axis A1
#define elbow_axis A2
#define arm_axis   A3
#define body_axis  A4

#define button_head_r  9
#define button_head_l 10
#define button_mouth  11

Servo mouth;
Servo head;
Servo wrist;
Servo elbow;
Servo arm;
Servo body;

int mouth_value;
int head_value_r;
int head_value_l;
int wrist_value;
int elbow_value;
int arm_value;
int body_value;

int wrist_1 = 20000;
int elbow_1 = 20000;
int arm_1   = 20000;
int body_1  = 20000;

int head_angle;
int wrist_angle;
int elbow_angle;
int arm_angle;
int body_angle;

void setup() {
  mouth.attach(3);
  head.attach(4);
  wrist.attach(5);
  elbow.attach(6);
  arm.attach(7);
  body.attach(8);
  pinMode(button_head_r,OUTPUT);
  pinMode(button_head_l,OUTPUT);
  pinMode(button_mouth,OUTPUT);
  Serial.begin(9600);
}

void loop() {
  wrist_value=analogRead(wrist_axis);
  elbow_value=analogRead(elbow_axis);
  arm_value=analogRead(arm_axis);
  body_value=analogRead(body_axis);
  head_value_r=digitalRead(button_head_r);
  head_value_l=digitalRead(button_head_l);
  mouth_value=digitalRead(button_mouth);

  wrist_value=map(wrist_value,0,1023,1,29);
  elbow_value=map(elbow_value,0,1023,1,29);
  arm_value=map(arm_value,0,1023,1,29);
  body_value=map(body_value,0,1023,1,29);

  if(wrist_value>=13&&wrist_value<=17) {
    wrist_value=15;
  }
  if(elbow_value>=13&&elbow_value<=17) {
    elbow_value=15;
  }
  if(arm_value>=13&&arm_value<=17) {
    arm_value=15;
  }
  if(body_value>=13&&body_value<=17) {
    body_value=15;
  }

  wrist_1 = wrist_1 - (wrist_value-15);
  elbow_1 = elbow_1 - (elbow_value-15);
  arm_1 = arm_1 - (arm_value-15);
  body_1 = body_1 - (body_value-15);

  if(wrist_1<1) wrist_1 = 1;
  if(wrist_1>20000) wrist_1 = 20000;
  if(elbow_1<1) elbow_1 = 1;
  if(elbow_1>20000) elbow_1 = 20000;
  if(arm_1<1) arm_1 = 1;
  if(arm_1>20000) arm_1 = 20000;
  if(body_1<1) body_1 = 1;
  if(body_1>20000) body_1 = 20000;

  wrist_angle = map(wrist_1,0,20000,0,180);
  elbow_angle = map(elbow_1,0,20000,0,180);
  arm_angle = map(arm_1,0,20000,0,180);
  body_angle = map(body_1,0,20000,0,180);

  wrist.write(wrist_angle);
  elbow.write(elbow_angle);
  arm.write(arm_angle);
  body.write(body_angle);

  if(head_value_r) {
    head.write(head_angle);
    head_angle++;
    if(head_angle>180)
      head_angle=180;
  }
  if(head_value_l) {
    head.write(head_angle);
    head_angle--;
    if(head_angle<1)
      head_angle=1;
  }
  if(mouth_value) {
    mouth.write(140);
    delay(1000);
    mouth.write(30);
  }
}
