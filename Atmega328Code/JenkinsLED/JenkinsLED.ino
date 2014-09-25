/* JenkinsStatus will turn the LED at pin 13 on/off depending
 * on the value serially read.
 */
const char BUILD_SUCCESS = '2';
const int LED_PIN = 3;


void setup() {
  Serial.begin(9600);
    Serial.println("Hello world");
  pinMode(LED_PIN, OUTPUT);
}

void loop() {
  if (Serial.available() > 0) {
      int sentBit = Serial.read();
      if (sentBit == BUILD_SUCCESS) {
    	  digitalWrite(LED_PIN, LOW);
      } else{
    	  digitalWrite(LED_PIN, HIGH);
      }
  }
}


