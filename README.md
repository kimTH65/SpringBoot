# SpringBoot - CRUD Practice

<h3>1. application.properties에 DB 접속 정보를 추가</h3>

<h5>
  　　application.properties : 외부 설정 파일로 애플리케이션 구동시 자동으로 로딩 되는 파일 <br><br>
  　　jdbc : 자바 어플리케이션과 데이터베이스의 브릿지 역할을 하며 JDK(자바 개발 키트)에 포함되어 있다 <br>
</h5>

<div align="center"><h6>practice/src/main/resources/application.properties</h6></div>

```
server.port=8090

spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3307/board
spring.datasource.username=root
spring.datasource.password=1111
```

#

<h4>SpringBoot - CRUD Project</h4>

