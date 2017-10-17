

void setup() {
  pinMode(23, INPUT);
   Serial.begin(9600);
}

void loop() {
  Serial.println(analogRead(23));//833=pH7
  delay(100);
}

