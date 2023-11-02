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

<h3>2. 사용할 데이터 Model(Entity) 생성 </h3>

<div align="center"><h6>practice/src/main/java/com/example/practice/entity/Board.java</h6></div>

```
package com.example.practice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private  String title;

    private  String content;
}
```

#

<h3>3. Repository 생성(JPARepository 사용) </h3>

<h5>
  　　Repository란 : Entity에 의해 생성된 DB에 접근하는 메서드 들을 사용하기 위한 인터페이스 <br><br>
  　　JPARepository : 자바 어플리케이션과 데이터베이스의 브릿지 역할을 하며 JDK(자바 개발 키트)에 포함되어 있다 <br>
</h5>

<div align="center"><h6>practice/src/main/java/com/example/practice/entity/Board.java</h6></div>

```

