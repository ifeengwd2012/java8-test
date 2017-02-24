package fn.wd.optional;

/**
 * Created by fengweidong on 2016/12/26.
 */
public class Page {


    private Integer id;
    private Question question;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", question=" + question +
                '}';
    }

    public Page(Integer id, Question question) {
        this.id = id;
        this.question = question;
    }

    public Page() {
    }
}
