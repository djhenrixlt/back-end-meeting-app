
# Visma2022Internship

Visma 2022 Internship Task: meeting spring boot app. 

Application ca create and delete meetings. 

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
]
 {
        "id": 2,
        "name": "Arnold Meeting May",
        "responsiblePerson": "Arnold",
        "description": "Arnold Meeting",
        "category": "SHORT",
        "type": "LIVE",
        "participant": [
            {
                "firstName": "ARNOLD",
                "lastName": "BOROW",
                "author": true
            }
        ],
        "author": {
            "firstName": "ARNOLD",
            "lastName": "BOROW",
            "author": true
        },
        "startDate": "2022-05-23",
        "endDate": "2022-05-24"
    },
    {
        "id": 3,
        "name": "Wednesday meeting ",
        "responsiblePerson": "Arnold",
        "description": "weekdays meeting",
        "category": "TEAM_BUILDING",
        "type": "LIVE",
        "participant": [
            {
                "firstName": "ARNOLD",
                "lastName": "BOROW",
                "author": false
            },
            {
                "firstName": "Hermione",
                "lastName": "Sterling",
                "author": false
            },
            {
                "firstName": "ELon",
                "lastName": "Musk",
                "author": false
            },
            {
                "firstName": "BORIS",
                "lastName": "Jons",
                "author": false
            }
        ],
        "author": {
            "firstName": "ARNOLD",
            "lastName": "BOROW",
            "author": false
        },
        "startDate": "2022-05-25",
        "endDate": "2022-05-26"
    }
]
```
#### New meeting:


```http
  POST /meetings
```
Send like this:


```json
 {
     For Update Meeting PUT /meetings add this line: "id": 3,
        "name": "Arnold Meeting May",
        "responsiblePerson": "Arnold",
        "description": "Arnold Meeting",
        "category": "SHORT",
        "type": "LIVE",
        "participant": [
            
            {
                "firstName": "ARNOLD",
                "lastName": "BOROW"
            },
             {
                "firstName": "ERIK",
                "lastName": "Bardock "
            }
         
        ],
        "author": {
              "firstName": "ARNOLD",
                "lastName": "BOROW"

        },
        "startDate": "2022-05-23",
        "endDate": "2022-05-24"
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
  /meetings/byStartFromDate
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
        "id": 2,
        "name": "Arnold Meeting May",
        "responsiblePerson": "Arnold",
        "description": "Arnold Meeting",
        "category": "SHORT",
        "type": "LIVE",
        "participant": [
            {
                "firstName": "ARNOLD",
                "lastName": "BOROW",
                "author": true
            }
        ],
        "author": {
            "firstName": "ARNOLD",
            "lastName": "BOROW",
            "author": true
        },
        "startDate": "2022-05-23",
        "endDate": "2022-05-24"
    },
    {
        "id": 4,
        "name": "Arnold Meeting May",
        "responsiblePerson": "Arnold",
        "description": "Arnold Meeting",
        "category": "SHORT",
        "type": "LIVE",
        "participant": [
            {
                "firstName": "ARNOLD",
                "lastName": "BOROW",
                "author": true
            },
            {
                "firstName": "ERIK",
                "lastName": "Bardock ",
                "author": false
            }
        ],
        "author": {
            "firstName": "ARNOLD",
            "lastName": "BOROW",
            "author": true
        },
        "startDate": "2022-05-23",
        "endDate": "2022-05-24"
    }
]
```



