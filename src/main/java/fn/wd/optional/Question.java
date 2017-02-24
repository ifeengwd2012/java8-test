package fn.wd.optional;

/**
 * Created by fengweidong on 2016/12/26.
 */
public class Question {

    private Integer id;
    private String title;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Question() {
    }

    public Question(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
