
import team.keephealth.model.ALSRecommend;
import team.keephealth.model.DataBase;

public class tests {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        System.out.println(dataBase.getScore(7L));
        //ALSRecommend alsRecommend = new ALSRecommend(dataBase.getScore());
        //System.out.println(alsRecommend.recommendUsers(1,5));
        /*for (int a:alsRecommend.recommendUsers(1,5)) {
            System.out.println(a);
        }*/
    }
}
