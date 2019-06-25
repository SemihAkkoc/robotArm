#include <Servo.h>

Servo mouth;
Servo head;
Servo wrist;
Servo elbow;
Servo arm;
Servo body;

String dataIn="";

//set to the origin points
unsigned int m1=50;
unsigned int h1=50;
unsigned int w1=50;
unsigned int e1=50;
unsigned int a1=50;
unsigned int b1=50;


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
    dataIn = Serial.readString();
    if(dataIn.startsWith("m")) {
      String data = dataIn.substring(1,dataIn.length());
      m1=data.toInt();
      mouth.write(m1);
    }
    if(dataIn.startsWith("h")) {
      String data = dataIn.substring(1,dataIn.length());
      h1=data.toInt();
      head.write(h1);
    }
    if(dataIn.startsWith("w")) {
      String data = dataIn.substring(1,dataIn.length());
      w1=data.toInt();
      wrist.write(w1);
    }
    if(dataIn.startsWith("e")) {
      String data = dataIn.substring(1,dataIn.length());
      e1=data.toInt();
      elbow.write(e1);
    }
    if(dataIn.startsWith("a")) {
      String data = dataIn.substring(1,dataIn.length());
      a1=data.toInt();
      arm.write(a1);
    }
    if(dataIn.startsWith("b")) {
      String data = dataIn.substring(1,dataIn.length());
      b1=data.toInt();
      body.write(b1);
    }
  }
}
