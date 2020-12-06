package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application implements EventHandler<ActionEvent> {

    Stage titleStage;
    Button exitbutton;
    int catChoice;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        titleStage = primaryStage;
        Scene catlistscene;
        Scene fightscene;

        //create cats
        Cat cat1 = new Cat("Hobbes", 20, 4, 0.7, 4);
        Cat cat2 = new Cat("Batman", 24, 3, 0.5, 4);
        Cat cat3 = new Cat("Angel", 18, 4, 0.8, 5);
        Cat cat4 = new Cat("Bessie", 22, 5, 0.6, 4);
        Cat cat5 = new Cat("Nikki", 16, 6, 0.9, 3);
        ImageView cat1iv = createCats("img/CatTabby.png");
        ImageView cat2iv = createCats("img/CatBlack.png");
        ImageView cat3iv = createCats("img/CatWhite.png");
        ImageView cat4iv = createCats("img/CatCow.png");
        ImageView cat5iv = createCats("img/CatSiamese.png");
        ImageView cat1iv2 = createCats("img/CatTabby.png");
        ImageView cat2iv2 = createCats("img/CatBlack.png");
        ImageView cat3iv2 = createCats("img/CatWhite.png");
        ImageView cat4iv2 = createCats("img/CatCow.png");
        ImageView cat5iv2 = createCats("img/CatSiamese.png");

        //Get background images
        FileInputStream bgfile = new FileInputStream("img/background1.png");
        Image bgim = new Image(bgfile);
        BackgroundImage bgimage = new BackgroundImage(bgim, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1,1,false, false, false, true));
        Background bg = new Background(bgimage);
        FileInputStream fbgfile = new FileInputStream("img/fightbg.png");
        Image fbgim = new Image(fbgfile);
        BackgroundImage fbgimage = new BackgroundImage(fbgim, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, false, false, false, true));
        Background fbg = new Background(fbgimage);

        //Scene 1: title screen
        BorderPane bpane = new BorderPane();
        bpane.setBackground(bg);
        Scene mainscene = new Scene(bpane, 1000, 700);

        //Scene 2: fight
        BorderPane fightpane = new BorderPane();
        fightpane.setBackground(fbg);
        fightscene = new Scene(fightpane, 1000, 700);
        VBox firstcat = new VBox(10);
        firstcat.setPadding(new Insets(40, 40, 40, 40));

        //Scene 3: cat list
        BorderPane catpane = new BorderPane();
        GridPane catgrid = new GridPane();
        catlistscene = new Scene(catpane, 1000, 700);
        catgrid.setHgap(100);
        catgrid.setVgap(20);
        catpane.setCenter(catgrid);
        catgrid.setAlignment(Pos.CENTER);
        catgrid.setPadding(new Insets(-80, 20, 20, 20));


        //TITLE PAGE
        //bottom bar
        HBox bottombar = new HBox();
        exitbutton = new Button("Exit");
        exitbutton.setOnAction(e -> closeProgram());
        exitbutton.setPrefSize(80,80);
        bottombar.getChildren().add(exitbutton);
        bpane.setBottom(bottombar);

        //basket button
        FileInputStream catbasketfile = new FileInputStream("img/catBasket.png");
        Image cbim = new Image(catbasketfile);
        ImageView cbview = new ImageView(cbim);
        cbview.setFitWidth(80);
        cbview.setPreserveRatio(true);
        Button basket = new Button();
        basket.setGraphic(cbview);
        basket.setOnAction(e -> titleStage.setScene(catlistscene));
        basket.setPrefSize(80,80);
        bottombar.getChildren().add(basket);

        //vbox for center
        VBox centermenu = new VBox(10);
        centermenu.setAlignment(Pos.CENTER);
        bpane.setCenter(centermenu);
        Label title = new Label("CAT GAME");
        title.setFont(new Font(40));
        Label choose = new Label("Choose your fighter:");
        choose.setFont(new Font(20));
        centermenu.getChildren().addAll(title, choose);

        //select fighter
        ToggleGroup fighters = new ToggleGroup();
        RadioButton tabby = new RadioButton("Hobbes");
        tabby.setToggleGroup(fighters);
        tabby.setSelected(true);
        RadioButton black = new RadioButton("Batman");
        black.setToggleGroup(fighters);
        RadioButton white = new RadioButton("Angel");
        white.setToggleGroup(fighters);
        RadioButton cow = new RadioButton("Bessie");
        cow.setToggleGroup(fighters);
        RadioButton siamese = new RadioButton("Nikki");
        siamese.setToggleGroup(fighters);
        centermenu.getChildren().addAll(tabby, black, white, cow, siamese);

        //fight button
        Button fight = new Button("Begin Battle");
        fight.setOnAction(e -> {
            if(tabby.isSelected()) {
                catChoice = 1;
                firstcat.getChildren().addAll(new Label("Hobbes"), cat1iv);
            } else if(black.isSelected()) {
                catChoice = 2;
                firstcat.getChildren().addAll(new Label("Batman"), cat2iv);
            } else if(white.isSelected()) {
                catChoice = 3;
                firstcat.getChildren().addAll(new Label("Angel"), cat3iv);
            } else if(cow.isSelected()) {
                catChoice = 4;
                firstcat.getChildren().addAll(new Label("Bessie"), cat4iv);
            } else {
                catChoice = 5;
                firstcat.getChildren().addAll(new Label("Nikki"), cat5iv);
            }
            titleStage.setScene(fightscene);
        });
        centermenu.getChildren().add(fight);

        //BATTLE
        //first cat
        firstcat.setAlignment(Pos.CENTER_LEFT);
        fightpane.setLeft(firstcat);

        //quit button
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            titleStage.setScene(mainscene);
            firstcat.getChildren().clear();
        });
        fightpane.setBottom(quitButton);

        //CAT LIST
        //background
        FileInputStream cbgfile = new FileInputStream("img/catpagebg.png");
        Image cbgim = new Image(cbgfile);
        BackgroundImage cbgbi = new BackgroundImage(cbgim, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, false, false, false, true));
        Background cbg = new Background(cbgbi);
        catpane.setBackground(cbg);

        //title
        Label catTitle = new Label("Available Cats");
        catTitle.setFont(new Font(40));
        catTitle.setPadding(new Insets(20, 20, 20, 20));
        catpane.setTop(catTitle);
        BorderPane.setAlignment(catTitle, Pos.CENTER);
        BorderPane.setAlignment(catgrid, Pos.CENTER);

        //cat 1: tabby
        catgrid.add(cat1iv2, 0, 0);
        Text cat1stats = listStats(cat1);
        catgrid.add(cat1stats, 0, 1);

        //cat 2: black
        catgrid.add(cat2iv2, 1, 0);
        Text cat2stats = listStats(cat2);
        catgrid.add(cat2stats, 1, 1);

        //cat 3: white
        catgrid.add(cat3iv2, 2, 0);
        Text cat3stats = listStats(cat3);
        catgrid.add(cat3stats, 2, 1);

        //cat 4: cow
        catgrid.add(cat4iv2, 3, 0);
        Text cat4stats = listStats(cat4);
        catgrid.add(cat4stats, 3, 1);

        //cat 5: siamese
        catgrid.add(cat5iv2, 4, 0);
        Text cat5stats = listStats(cat5);
        catgrid.add(cat5stats, 4, 1);

        //home button
        Button homebutton = new Button("Return to Home Page");
        homebutton.setOnAction(e -> titleStage.setScene(mainscene));
        homebutton.setPrefSize(160, 40);
        catpane.setBottom(homebutton);

        //set stage
        titleStage.setOnCloseRequest(e -> closeProgram());
        titleStage.setTitle("Cat Game");
        titleStage.setScene(mainscene);
        titleStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }

    private void closeProgram() {
        titleStage.close();
    }

    public ImageView createCats(String s) throws FileNotFoundException {

        FileInputStream cat1file = new FileInputStream(s);
        Image cat1im = new Image(cat1file);
        ImageView cat1iv = new ImageView(cat1im);

        cat1iv.setFitWidth(100);
        cat1iv.setPreserveRatio(true);

        return cat1iv;
    }

    public Text listStats(Cat c){
        Text t = new Text();
        t.setText("Name: " + c.getName() + "\nHP: " + c.getHp() + "\nDamage: " + c.getDamage() + "\nBlock: " + c.getBlock() + "\nHeal: " + c.getHeal());
        return t;
    }
}
