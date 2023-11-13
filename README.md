
<div align="right">
  <h5>
    Language : 
    <a href="JP.md">日本語</a> 
      ,
    <a href="US.md">English</a> 
  </h5>
</div>

# SpringBoot - CRUD Practice

<br>

<h3>1. application.properties에 DB 접속 정보를 추가</h3>

<h5>

  - application.properties : 외부 설정 파일로 애플리케이션 구동시 자동으로 로딩 되는 파일 <br><br>
  - jdbc : 자바 어플리케이션과 데이터베이스의 브릿지 역할을 하며 JDK(자바 개발 키트)에 포함되어 있음
</h5>

<div align="center"><h5><a href="practice/src/main/resources/application.properties">application.properties</a></h5></div>

```
server.port=8090

spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3307/board
spring.datasource.username=root
spring.datasource.password=1111
```

#

<h3>2. 사용할 데이터 Model(Entity) 생성 </h3>

<div align="center">
  <h5>
    <a href="practice/src/main/java/com/example/practice/entity/Board.java">
      Entity
    </a>
  </h5>
</div>

```
  .
  .
  .

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
 
 - Repository란 : Entity에 의해 생성된 DB에 접근하는 메서드 들을 사용하기 위한 인터페이스 <br><br>
 - JPARepository : Spring Data JPA에서 제공하는 JPA 구현을 위한 인터페이스, 간단히 DB에 Create/Read/Update/Delete 쿼리를 수행 가능
</h5>

<div align="center">
  <h5>
    <a href="practice/src/main/java/com/example/practice/repository/BoardRepository.java">
      Repository
    </a>
  </h5>
</div>

```
  .
  .
  .

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
}
```

#

<h3>4. Service(비즈니스 로직 수행) 생성</h3>

<div align="center">
  <h5>
    <a href="practice/src/main/java/com/example/practice/service/BoardService.java">
      Service
    </a>
  </h5>
</div>

```
  .
  .
  .

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board){
        boardRepository.save(board);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    public Board boardView(Integer id){

        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id){

       boardRepository.deleteById(id);
    }

}
```

#

<h3>5. Controller(사용자의 요청 처리) 생성</h3>

<div align="center">
  <h5>
    <a href="practice/src/main/java/com/example/practice/controller/BoardController.java">
      Controller
    </a>
  </h5>
</div>

```
  .
  .
  .

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWrite(){
        return "BoardWrite";
    }

    @GetMapping("/board/boardWriteDo")
    public String boardWriteDO(Board board){

        boardService.write(board);
        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list",boardService.boardList());
        return "BoardList";
    }
  .
  .
  .

```

#

<h3>6. View 생성</h3>

<div align="center">
  <h5>
    <a href="practice/src/main/resources/templates">
      View
    </a>
  </h5>
</div>

```
  .
  .
  .

<body>
<div class="layout">
    <input type="button" value="글쓰기" onclick="location.href='/board/write'" />
    <table>
        <thead>
            <tr>
                <th>글번호</th>
                <th>제목</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="board : ${list}">
                <td th:text="${board.id}"></td>
                <td >
                    <a th:text="${board.title}" th:href="@{/board/view(id=${board.id})}"></a>
                </td>
            </tr>
  .
  .
  .

```
