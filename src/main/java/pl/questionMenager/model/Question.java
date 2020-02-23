package pl.questionMenager.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuestion")
    private Integer idQuestion;

    @NonNull
    @Column(name = "question")
    private String question;

    @NonNull
    @Column(name = "answer")
    private String answer;

    @Column(name = "difficultyLevel")
    private String difficultyLevel;

    public Question(@NonNull String question, @NonNull String answer) {
        this.question = question;
        this.answer = answer;
        this.difficultyLevel = DifficultyLevel.EMPTY.toString();
    }

    public Question(@NonNull String question, String answer, DifficultyLevel difficultyLevel) {
        this.question = question;
        this.answer = answer;
        this.difficultyLevel = difficultyLevel.toString();
    }

    public Question(@NonNull String question, @NonNull DifficultyLevel difficultyLevel) {
        this.question = question;
        this.answer = null;
        this.difficultyLevel = difficultyLevel.toString();
    }

}
