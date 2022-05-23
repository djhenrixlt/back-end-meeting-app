
# Visma2022Internship

Visma 2022 Internship Task: meeting spring boot app. 

Application can create and delete meetings. 

You can add a person to the meeting and delete the person. 

You can search meeting(s) by id, name, description, 

responsible person, category, type, dates, by number of persons in meeting. 


## What was used in project 

I did use Java 11 and Java framework Spring Boot for this project 

 and Lombok dependency. 

Did used Jackson dependency for reading Json file and write  

back to file. 

For mapper did use mapstruct dependency. 

 
Did test everything with "Postman". 


## Tech Stack

**Client:** Java, Spring Boot

**Server:** Json file


## API endpoints

#### Get all Meetings:

```http
  GET  /meetings
```


You will Get like this:
```json
]{
  "id" : 6,
  "name" : "Dorin Meeting",
  "responsiblePerson" : "Dorin",
  "description" : "New Meeting",
  "category" : "CODE_MONKEY",
  "type" : "LIVE",
  "participant" : [ {
    "firstName" : "Dorin",
    "lastName" : "Gadise",
    "author" : true,
    "timeNow" : "2022-05-23 16:39:41"
  } ],
  "author" : {
    "firstName" : "Dorin",
    "lastName" : "Gadise",
    "author" : true,
    "timeNow" : "2022-05-23 16:39:41"
  },
  "startDate" : "2022-05-25",
  "endDate" : "2022-05-26"
}, {
  "id" : 7,
  "name" : "Salmon Meeting",
  "responsiblePerson" : "Salmon",
  "description" : "New Meeting",
  "category" : "CODE_MONKEY",
  "type" : "LIVE",
  "participant" : [ {
    "firstName" : "Salmon",
    "lastName" : "Norvi",
    "author" : true,
    "timeNow" : "2022-05-23 17:04:17"
  } ],
  "author" : {
    "firstName" : "Salmon",
    "lastName" : "Norvi",
    "author" : true,
    "timeNow" : "2022-05-23 17:04:17"
  },
  "startDate" : "2022-05-25",
  "endDate" : "2022-05-26"
} ]
```
#### New meeting:


```http
  POST /meetings
```
Send like this:


```json
 {
     For Update Meeting PUT /meetings add this line: "id": 3,
       "name" : "Salmon Meeting",
  "responsiblePerson" : "Salmon",
  "description" : "New Meeting",
  "category" : "CODE_MONKEY",
  "type" : "LIVE",
  "participant" : [ {
    "firstName" : "Salmon",
    "lastName" : "Norvi"
  }
  ],
  "author" : {
    "firstName" : "Salmon",
    "lastName" : "Norvi"
  },
   {
    "firstName" : "Salmon2",
    "lastName" : "Norvi2"
  },
  
  "startDate" : "2022-05-25",
  "endDate" : "2022-05-26"
    }
```
#### Add Person to meeting
```http
 PUT /{meetingId}/add
```
```json
http://localhost:8080/meetings/3/add
{
"firstName": "JACK",
"lastName": "King"
}
```
#### Get by description and almots the same for rest:
```http
  Get /meetings/byDescription

  /meetings/byName
  key = name        
  /meetings/byDescription
  key = description 
  /meetings/byType
  key = type 
  /meetings/byCategory
  key = category  
  /meetings/byResPerson
  key = person  
  /meetings/byStartDate
  key = date 
  /meetings/bytDate
  key = start 
  key = end
  /meetings/byNumberUsers
  key = number
```

| KEY | VALUE     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `description`      | Arnold Meeting |  |

You will Get:
```json
[
    {
      "id" : 1,
  "name" : "Arnold Meeting May",
  "responsiblePerson" : "Arnold",
  "description" : "Arnold Meeting",
  "category" : "SHORT",
  "type" : "LIVE",
  "participant" : [ {
    "firstName" : "ARNOLD",
    "lastName" : "BOROW",
    "author" : true,
    "timeNow" : "2022-05-23 16:08:05"
  }, {
    "firstName" : "JAC",
    "lastName" : "King",
    "author" : false,
    "timeNow" : "2022-05-23 16:08:05"
  }, {
    "firstName" : "JAC",
    "lastName" : "MARON",
    "author" : false,
    "timeNow" : "2022-05-23 16:08:05"
  }, {
    "firstName" : "Nikita",
    "lastName" : "Baroc",
    "author" : false,
    "timeNow" : "2022-05-23 16:09:04"
  }, {
    "firstName" : "Melek",
    "lastName" : "Zahari",
    "author" : false,
    "timeNow" : "2022-05-23 16:40:20"
  }, {
    "firstName" : "Emmanuil",
    "lastName" : "Murugan",
    "author" : false,
    "timeNow" : "2022-05-23 17:01:10"
  } ],
  "author" : {
    "firstName" : "ARNOLD",
    "lastName" : "BOROW",
    "author" : true,
    "timeNow" : "2022-05-23 16:08:05"
  },
  "startDate" : "2022-05-23",
  "endDate" : "2022-05-24"
    }
]
```



