# SpringLv2

### API 명세서
|URI|HTTP METHOD|Request|Response|
|---|---|---|---|
|/user/signup|POST|{"username":String, "password":String}|{"msg":String, "status":HttpStatus}|
|/user/login|POST|{"username":String, "password":String}|header:{"Authorization":"Bearer <JWT>"}, body:{"msg":String, "status":HttpStatus}|
|/memo/create|POST|header:{"Authorization":"Bearer <JWT>"}, body:{"title":String, "content":String}|{"title":String, "name":String, "content":String, "modifiedAt":LocalDate}|
|/memo/list|GET|-|[{"title":String, "name":String, "content":String, "modifiedAt":LocalDate},...]|
|/memo/{id}|GET|-|{"title":String, "name":String, "content":String, "modifiedAt":LocalDate}|
|/memo/{id}|PUT|header:{"Authorization":"Bearer <JWT>"}, body:{"title":String, "content":String}|{"title":String, "name":String, "content":String, "modifiedAt":LocalDate}|
|/memo/{id}|DELETE|header:{"Authorization":"Bearer <JWT>"}|{"msg":String, "status":HttpStatus}|
