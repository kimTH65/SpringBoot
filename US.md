# SpringBoot - CRUD Practice
<div align="right">
  <h5>
    Language : 
    <a href="README.md">한국어</a> 
      ,
    <a href="JP.md">日本語</a> 
  </h5>
</div>

<h3>1. Add DB connection information to application.properties</h3>

<h5>

- application.properties : An external configuration file that automatically loads when the application runs <br><br>
- jdbc : acts as a bridge between Java applications and databases and is included in JDK (Java Development Kit)
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

<h3>2. Create Data Model(Entity) </h3>

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

<h3>3. Create Repository (using JPAR repository) </h3>

<h5>
 
 - Repository : Interface for using methods to access DB created by Entity<br><br>
 - JPARepository : Interface for JPA implementation provided by Spring Data JPA, which can simply perform Create/Read/Update/Delete queries in DB
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

<h3>4. Create Service(Business Logic)</h3>

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

<h3>5. Create Controller</h3>

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

<h3>6. Create View</h3>

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
