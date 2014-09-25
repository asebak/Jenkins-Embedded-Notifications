/* JenkinsStatus will turn the LED at pin 13 on/off depending
 * on the value serially read.
 */
const char BUILD_UNSTABLE = '0';
const char BUILD_BUILDING = '1';
const char BUILD_SUCCESS = '2';
const int LED_PIN = 3;


void setup() {
  Serial.begin(9600);
  Serial.println("Begin Serial Communication");
  pinMode(LED_PIN, OUTPUT);
}

void loop() {
  if (Serial.available() > 0) {
      int receivedBit = Serial.read();
      if (receivedBit == BUILD_SUCCESS) {
    	  digitalWrite(LED_PIN, HIGH);
      } else{
    	  digitalWrite(LED_PIN, LOW);
      }
  }
}


