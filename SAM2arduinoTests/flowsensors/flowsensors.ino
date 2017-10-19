float waterFlow = 0;

void setup() {
  //pinMode(18, INPUT);
  attachInterrupt(15, flowSensor, RISING); //flowsensor setup
  Serial.begin(9600);
}

void loop() {
  Serial.println(waterFlow);//mL
  delay(100);
}

void flowSensor()   //measure the quantity of square wave
{
  waterFlow += 1.0 / 0.450;//450 pulses per liter
}
