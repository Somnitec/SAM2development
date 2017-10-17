int pin = 5;

void setup() {
  Serial.begin(9600);
  pinMode(pin,INPUT);
}

void loop() {
  Serial.println(analogRead(pin));
  delay(100);
}
