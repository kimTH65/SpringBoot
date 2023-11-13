# SpringBoot - CRUD Practice

<div align="right">
  <h5>
    Language : 
    <a href="README.md">한국어</a> 
      ,
    <a href="US.md">English</a> 
  </h5>
</div>

<h3>1. application.propertiesにDB接続情報を追加</h3>

<h5>

- application.properties : 外部設定ファイルでアプリケーション駆動時に自動的にロードされるファイル<br><br>
- jdbc : JAVAアプリケーションとデータベースのブリッジの役割を果たし、JDK(JAVA開発キット)に含まれている
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

<h3>2. 使用するデータ Model(Entity) 生成 </h3>

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

<h3>3. Repository生成(JPARepositoryを使用) </h3>

<h5>
 
- Repositoryとは : Entityによって生成されたDBにアクセスするメソッドを使用するためのインターフェース<br><br>
- JPARepository : Spring Data JPAが提供するJPA実装のためのインターフェース、簡単にDBにCreate/Read/Update/Deleteクエリを実行可能
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

<h3>4. Service(ビジネスロジックの実行)の作成</h3>

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

<h3>5. Controller(ユーザーの要求処理)の生成</h3>

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

<h3>6. View 生成</h3>

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
