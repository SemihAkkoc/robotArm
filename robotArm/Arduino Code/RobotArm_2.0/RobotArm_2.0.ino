#include <Servo.h>

Servo mouth;
Servo head;
Servo wrist;
Servo elbow;
Servo arm;
Servo body;

String data="";

//set to the origin points
unsigned int m1=90;
unsigned int h1=90;
unsigned int w1=90;
unsigned int e1=90;
unsigned int a1=90;
unsigned int b1=90;


void setup() {
  mouth.attach(3);
  head.attach(4);
  wrist.attach(5);
  elbow.attach(6);
  arm.attach(7);
  body.attach(8);
  Serial.begin(9600);
  Serial.setTimeout(10);
  mouth.write(m1);
  head.write(h1);
  wrist.write(w1);
  elbow.write(e1);
  arm.write(a1);
  body.write(b1);
}

void loop() {
  if(Serial.available()>0) {
    data = Serial.readString();
    if(data.startsWith("s1r")) {
      mouth.write(m1);
      m1=m1+10;
      if(m1>179)
        m1=179;
    }
    if(data.startsWith("s1l")) {
      mouth.write(m1);
      m1=m1-10;
      if(m1<1)
        m1=1;
    }
    if(data.startsWith("s2r")) {
      head.write(h1);
      h1=h1+10;
      if(h1>179)
        h1=179;
    }
    if(data.startsWith("s2l")) {
      head.write(h1);
      h1=h1-10;
      if(h1<1)
        h1=1;
    }
    if(data.startsWith("s3r")) {
      wrist.write(w1);
      w1=w1+10;
      if(w1>179)
        w1=179;
    }
    if(data.startsWith("s3l")) {
      wrist.write(w1);
      w1=w1-10;
      if(w1<1)
        w1=1;
    }
    if(data.startsWith("s4r")) {
      elbow.write(e1);
      e1=e1+10;
      if(e1>179)
        e1=179;
    }
    if(data.startsWith("s4l")) {
      elbow.write(e1);
      e1=e1-10;
      if(e1<1)
        e1=1;
    }
    if(data.startsWith("s5r")) {
      arm.write(a1);
      a1=a1+10;
      if(a1>179)
        a1=179;
    }
    if(data.startsWith("s5l")) {
      arm.write(a1);
      a1=a1-10;
      if(a1<1)
        a1=1;
    }
    if(data.startsWith("s6r")) {
      body.write(b1);
      b1=b1+10;
      if(b1>179)
        b1=179;
    }
    if(data.startsWith("s6l")) {
      body.write(b1);
      b1=b1-10;
      if(b1<1)
        b1=1;
    }
  }
}
