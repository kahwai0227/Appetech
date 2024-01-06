#include <ESP8266Firebase.h>
#include <ESP8266WiFi.h>

// Firebase credentials
#define _SSID "kahwai"          // Your WiFi SSID
#define _PASSWORD "Bg017A125!"      // Your WiFi Password
#define REFERENCE_URL "https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app/"  // Your Firebase project reference url

Firebase firebase(REFERENCE_URL);

#define red_led D1
#define green_led D2
#define photoPin A0
#define buzzer D0

String table_no = "Table 1/";
String location = "Arked Meranti/";
String data = "";
int photoVal = 0;
int booking_time = 0;
int current_time = 0;
int time_limit = 100000;
bool booked = 0;
bool occupied = 0;

void red_on() {
  digitalWrite(red_led, HIGH);
  digitalWrite(green_led, LOW);
}

void green_on() {
  digitalWrite(red_led, LOW);
  digitalWrite(green_led, HIGH);
}

void check_occupied() {
  photoVal = analogRead(photoPin);
  if (photoVal>1000) {
    occupied = 0;
  } else {
    occupied = 1;
  }
}

void check_available_table() {
  if (occupied) {
    if (booked) {
      digitalWrite(buzzer, LOW);
    } 
    else {
      booking_time = 0;
      current_time = 0;
      red_on();
      digitalWrite(buzzer, HIGH);
    }
  } 
  else {
    if (booked) {
      if(booking_time == 0){
        booking_time = millis();
      }
      current_time = millis();
      red_on();
      digitalWrite(buzzer, HIGH);
      if (current_time - booking_time >= time_limit) {
        removeBooking();
        green_on();
      }
    } 
    else {
      digitalWrite(buzzer, HIGH);
      green_on();
    }
  }
}
void removeBooking(){
  booked = 0;
  data = "arked/" + location + table_no + "bookingUID";
  String uid = firebase.getString(data);
  data = "arked/" + location + table_no + "tableBooked"; firebase.setBool(data, booked);
  data = "arked/" + location + table_no + "bookingUID"; firebase.deleteData(data);
  data = "arked/" + location + table_no + "bookingTime"; firebase.deleteData(data);
  data = "arked/" + location + table_no + "bookingUserContact"; firebase.deleteData(data);
  data = "users/" + uid + "/" + "booking"; firebase.deleteData(data);
}

void debug() {
  Serial.print("Occupied = ");
  Serial.println(occupied);
  Serial.print("Booked = ");
  Serial.println(booked);
  Serial.print("Sensor = ");
  Serial.println(photoVal);
}

void setup() {
  Serial.begin(115200);
  pinMode(red_led, OUTPUT);
  pinMode(green_led, OUTPUT);
  pinMode(buzzer, OUTPUT);

  green_on();
  digitalWrite(buzzer, HIGH);

  WiFi.mode(WIFI_STA);
  WiFi.disconnect();
  delay(1000);

  // Connect to WiFi
  Serial.println();
  Serial.println();
  Serial.print("Connecting to: ");
  Serial.println(_SSID);
  WiFi.begin(_SSID, _PASSWORD);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print("-");
  }

  Serial.println("");
  Serial.println("WiFi Connected");

  // Print the IP address
  Serial.print("IP Address: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
}

void loop() {
  check_occupied();
  booked = firebase.getBool("arked/" + location + table_no + "tableBooked");
  check_available_table();  
  debug(); 
  data = "arked/" + location + table_no + "tableOccupied"; firebase.setBool(data, occupied);
  delay(10); // Add a small delay to prevent excessive Firebase updates
}


