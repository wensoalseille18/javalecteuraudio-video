/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetjavafx;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Blemy
 */
public class ProjetJavaFX extends Application {

    ClassAtrib nn = new ClassAtrib();
    int id, comp;
    String nom, chemin;
    Stage stage;
    BorderPane bpane = new BorderPane();
    ColorPicker colorPick = new ColorPicker();
    Scene scn = new Scene(bpane, 500, 500);
    Media media = null;
    MediaPlayer player = null;
    MediaView view = null;
    FileChooser chooser = null;
    Slider progressBar = new Slider();
    private File fichier;

    Slider volume = new Slider();
    public ArrayList<String> list = new ArrayList<>();
    TableView<ClassAtrib> tblV = new TableView<>();
    private Boolean tbVid = false, media_cours = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    public void start(Stage wind) throws Exception {
        stage = wind;
        creerFenetre();
        showFenetre();
        buttonFoot();
        creerMenu();
    } 

    void creerFenetre() {
        Image image = new Image("packageImage\\\\FolderImage\\\\Casque.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO,
                        BackgroundSize.AUTO, false, false, true, false));
        Background background = new Background(backgroundImage);
        bpane.setBackground(background);
        stage.setTitle("Authentic Player");
        stage.getIcons().add(new Image("packageImage\\FolderImage\\play.png"));
        stage.setScene(scn);
    }   
            
    void showFenetre() {
        stage.setMaximized(true);
        stage.show();
    }

    void creerMenu() {
        Image imagFlech = new Image("packageImage\\FolderImage\\fleche-deroulante.png");
        ImageView imFlch = new ImageView(imagFlech);
        imFlch.setFitWidth(9);
        imFlch.setFitHeight(9);

        Image imagFlech1 = new Image("packageImage\\FolderImage\\fleche-deroulante.png");
        ImageView imFlch1 = new ImageView(imagFlech1);
        imFlch1.setFitWidth(9);
        imFlch1.setFitHeight(9);

        Image imagFlech2 = new Image("packageImage\\FolderImage\\fleche-deroulante.png");
        ImageView imFlch2 = new ImageView(imagFlech2);
        imFlch2.setFitWidth(9);
        imFlch2.setFitHeight(9);

        Image imagFlech3 = new Image("packageImage\\FolderImage\\fleche-deroulante.png");
        ImageView imFlch3 = new ImageView(imagFlech3);
        imFlch3.setFitWidth(9);
        imFlch3.setFitHeight(9);

        Image imagFlech4 = new Image("packageImage\\FolderImage\\fleche-deroulante.png");
        ImageView imFlch4 = new ImageView(imagFlech4);
        imFlch4.setFitWidth(9);
        imFlch4.setFitHeight(9);

        MenuBar menubar = new MenuBar();
        Menu fichier = new Menu("Fichier");
        Menu musique = new Menu("Musique");
        Menu video = new Menu("Video");
        Menu apropos = new Menu("Apropos");
        Menu affichage = new Menu("Affichage");
        apropos.setOnAction(eh -> tabView());
        fichier.setGraphic(imFlch);
        musique.setGraphic(imFlch1);
        video.setGraphic(imFlch2);
        apropos.setGraphic(imFlch3);
        affichage.setGraphic(imFlch4);

        //sous menu
        Image imagMusic = new Image("packageImage\\FolderImage\\music.png");
        ImageView imgMusic = new ImageView(imagMusic);
        imgMusic.setFitWidth(20);
        imgMusic.setFitHeight(20);

        Image imagVideo = new Image("packageImage\\FolderImage\\fichier-video.png");
        ImageView imgVideo = new ImageView(imagVideo);
        imgVideo.setFitWidth(20);
        imgVideo.setFitHeight(20);

        Image iconExit = new Image("packageImage\\FolderImage\\se-deconnecter.png");
        ImageView iconExi = new ImageView(iconExit);
        iconExi.setFitWidth(20);
        iconExi.setFitHeight(20);

        // Sous Menus
        MenuItem ouvrirVideo = new MenuItem("Ouvrir Media");
        MenuItem ouvrirMusic = new MenuItem("Media Music");
        ouvrirVideo.setOnAction(e -> {
            ouvrirMedia();
            tabView();
        });

        ouvrirMusic.setOnAction(e -> tabView());
        MenuItem exit = new MenuItem("Quitter");
        exit.setOnAction(e -> exitStage());

        MenuItem background = new MenuItem("Sélectionner la couleur");
        ColorPicker backgColor = new ColorPicker();
        backgColor.setOnAction(e -> {
            Color color = backgColor.getValue();
            bpane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });

        MenuItem max = new MenuItem("Maximiser");
        max.setOnAction(eh -> maximiser());
        MenuItem min = new MenuItem("Minimiser");
        min.setOnAction(eh -> minimiser());
        MenuItem full = new MenuItem("Plein Ecran");
        full.setOnAction(eh -> pleinEcran());

        background.setGraphic(backgColor);
        ouvrirVideo.setGraphic(imgVideo);
        ouvrirMusic.setGraphic(imgMusic);
        exit.setGraphic(iconExi);
        fichier.getItems().addAll(ouvrirVideo, exit);
        affichage.getItems().addAll(background, max, min, full);
        menubar.getMenus().addAll(fichier, affichage, musique, video, apropos);

        bpane.setTop(menubar);
    }

    void buttonFoot() {
        HBox hbx = new HBox(25);

        Button pause = new Button("Play");
        Image imagPausez = new Image("packageImage\\FolderImage\\play-button.png");
        ImageView imaPauz = new ImageView(imagPausez);
        imaPauz.setFitWidth(25);
        imaPauz.setFitHeight(20);
        pause.setGraphic(imaPauz);

        Image imagPause = new Image("packageImage\\FolderImage\\play-button.png");
        ImageView imaPau = new ImageView(imagPause);
        imaPau.setFitWidth(25);
        imaPau.setFitHeight(20);
        pause.setGraphic(imaPau);
        Button next = new Button("Next");
        Image imagNxt = new Image("packageImage\\FolderImage\\fleche-droite.png");
        ImageView imagNext = new ImageView(imagNxt);
        imagNext.setFitWidth(25);
        imagNext.setFitHeight(20);
        next.setGraphic(imagNext);

        Button precedent = new Button("Previous");
        Image imagPrece = new Image("packageImage\\FolderImage\\fleche-gauche.png");
        ImageView imagPrecednt = new ImageView(imagPrece);
        imagPrecednt.setFitWidth(25);
        imagPrecednt.setFitHeight(20);
        precedent.setGraphic(imagPrecednt);

        Button replay = new Button("Replay");
        Image imagReplay = new Image("packageImage\\FolderImage\\replayy.png");
        ImageView imagRepla = new ImageView(imagReplay);
        imagRepla.setFitWidth(25);
        imagRepla.setFitHeight(20);
        replay .setGraphic(imagRepla);

        //Play
        Image imagPlay = new Image("packageImage\\FolderImage\\pause.png");
        ImageView imagPla = new ImageView(imagPlay);
        imagPla.setFitWidth(25);
        imagPla.setFitHeight(20);

        //stop
        Button stop = new Button("Stop");
        Image imagStop = new Image("packageImage\\FolderImage\\stop.png");
        ImageView imagSto = new ImageView(imagStop);
        imagSto.setFitWidth(25);
        imagSto.setFitHeight(20);
        stop.setGraphic(imagSto);

        Button mute = new Button("Mute");
        Image imagSilen = new Image("packageImage\\FolderImage\\silencieux.png");
        ImageView imagSilenc = new ImageView(imagSilen);
        imagSilenc.setFitWidth(25);
        imagSilenc.setFitHeight(20);
        mute.setGraphic(imagSilenc);

        Image imagDemute = new Image("packageImage\\FolderImage\\monter-le-son.png");
        ImageView imagDemut = new ImageView(imagDemute);
        imagDemut.setFitWidth(25);
        imagDemut.setFitHeight(20);
        //mute.setGraphic(imagDemut);

        Button agrandir = new Button();
        Image imagAgrand = new Image("packageImage\\FolderImage\\plein-ecran.png");
        ImageView imagAgrandir = new ImageView(imagAgrand);
        imagAgrandir.setFitWidth(25);
        imagAgrandir.setFitHeight(20);
        agrandir.setGraphic(imagAgrandir);

        mute.setOnAction(e -> {
            if (player != null) {
                if (player.isMute()) {
                    player.setMute(false);
                    mute.setText("Mute");
                    imagSilenc.setImage(imagSilen);
                } else {
                    player.setMute(true);
                    mute.setText("DeMute");
                    imagDemut.setImage(imagDemute);
                }
            }
        });

        stop.setOnAction(e -> {
            if (player != null) {
                if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                    player.stop();
                    stop.setText("Play");
                    imagSto.setImage(imagPausez);
                } else {
                    player.play();
                    stop.setText("Stop");
                    imagSto.setImage(imagStop);

                }
            }
        });

        pause.setGraphic(imaPau);
        pause.setOnAction(event -> {
            if (player != null) {
                if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                    player.pause();
                    pause.setText("Play");
                    imaPau.setImage(imagPause);
                } else {
                    player.play();
                    pause.setText("Pause");
                    imaPau.setImage(imagPlay);
                }
            }
        });

        agrandir.setOnAction(eh -> {
            if (player != null) {
                view.setFitWidth(1000);

            }
        });

        replay.setOnAction(eh
                -> {
            if (player != null) {
                player.seek(Duration.ZERO);
                player.play();
            }

        });

        next.setOnAction(eh -> {

            player.stop();
            tblV.getSelectionModel().selectNext();
            nn = tblV.getSelectionModel().getSelectedItem();
            fichier = new File(nn.getChemin());
            player = new MediaPlayer(new Media(fichier.toURI().toString()));
            view = new MediaView(player);
            bpane.setCenter(view);
            player.setAutoPlay(true);
        });

        precedent.setOnAction(eh -> {
            player.stop();
            tblV.getSelectionModel().selectPrevious();
            nn = tblV.getSelectionModel().getSelectedItem();
            fichier = new File(nn.getChemin());
            player = new MediaPlayer(new Media(fichier.toURI().toString()));
            view = new MediaView(player);
            bpane.setCenter(view);
            player.setAutoPlay(true);
        });

        volume.setPadding(new Insets(0, 8, 10, 10));
        volume.setShowTickLabels(true);
        volume.setValue(100);
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        progressBar.setPadding(new Insets(30, -1200, 1, 30));

        hbox.getChildren().add(progressBar);

        hbx.getChildren().addAll(precedent, pause, next, stop, replay, agrandir, mute, volume);
        hbx.setPadding(new Insets(10));
        hbx.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hbox, hbx);
        bpane.setBottom(vbox);
    }

    private void tabView() {
        TableColumn<ClassAtrib, String> col1 = new TableColumn<>("Id");
        TableColumn<ClassAtrib, String> col2 = new TableColumn<>("Nom");
        TableColumn<ClassAtrib, String> col3 = new TableColumn<>("Emplacement");

        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col3.setCellValueFactory(new PropertyValueFactory<>("chemin"));

        tblV.getColumns().addAll(col1, col2, col3);
        tblV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        if (!tbVid) {
            bpane.setRight(tblV);
            tbVid = true;
        } else {
            Alert error = new Alert(AlertType.WARNING);
            error.setTitle(" " + tbVid);
            error.setHeaderText("Erreur !");
            error.setContentText(" ");
            error.showAndWait();
        }

    }

    private void ouvrirMedia() {
        chooser = new FileChooser();
        chooser.setTitle("Choisir musique ou video");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("audio/video", "*.mp3", "*.mp4", "*.3gp", "*.flv", "*.ogg", "*.wmv", "*.wav", "*.wma"));
        List<File> fileselect = chooser.showOpenMultipleDialog(null);
        if (fileselect != null) {
            for (File file : fileselect) {
                comp++;
                id = comp;
                nom = file.getName();
                chemin = file.getPath();
                tblV.getItems().addAll(new ClassAtrib(id, nom, chemin));
                list.add(id + ";" + nom + ";" + chemin + ";\n");

                try {
                    media = new Media(file.toURI().toURL().toString());
                    player = new MediaPlayer(media);
                    view = new MediaView(player);

                    bpane.setCenter(view);

                } catch (MalformedURLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
                tblV.setOnMouseClicked(eh -> {
                    if (media_cours) {
                        player.stop();
                        media_cours = false;
                    }
                    nn = tblV.getSelectionModel().getSelectedItem();
                    if (!media_cours) {
                        player.stop();
                        fichier = new File(nn.getChemin());
                        media = new Media(fichier.toURI().toString());
                        player = new MediaPlayer(media);
                        player.currentTimeProperty().addListener((ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) -> {
                            progressBar.setValue(newValue.toSeconds());
                        });
                    }

                    view = new MediaView(player);
                    player.play();
                    bpane.setCenter(view);
                });

                volume.setValue(player.getVolume() * 100);
                volume.valueProperty().addListener((Observable observable) -> {
                    player.setVolume(volume.getValue() / 100);
                });

                player.currentTimeProperty().addListener((ObservableValue<? extends javafx.util.Duration> observable0,
                        javafx.util.Duration oldValue, javafx.util.Duration newValue) -> {
                    progressBar.setValue(newValue.toSeconds());
                });

                progressBar.setOnMousePressed((MouseEvent event1) -> {
                    player.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                });

                progressBar.setOnMouseDragged((MouseEvent event1) -> {
                    player.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                });

                player.setOnReady(() -> {
                    javafx.util.Duration total = media.getDuration();
                    progressBar.setMax(total.toSeconds());
                });

                scn.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.SPACE) {
                        if (player != null) {
                            MediaPlayer.Status status = player.getStatus();

                            if (player.getStatus() == MediaPlayer.Status.PLAYING) {

                                player.pause();
                            } else {
                                player.play();
                            }
                        }
                    }
                });

            }
        }
    }

    private void exitStage() {
        stage.close();
    }

    private void maximiser() {
        stage.setMaximized(true);
    }

    private void minimiser() {
        stage.setIconified(true);
    }

    private void pleinEcran() {
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Pressez CTRL+X pour sortir");
        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("CTRL+X"));
    }
}
