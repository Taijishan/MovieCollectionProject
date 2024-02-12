import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;
    private static ArrayList<String> actors = new ArrayList<String>();
    private static ArrayList<String> genres = new ArrayList<String>();
    private static ArrayList<Movie> action = new ArrayList<Movie>();
    private static ArrayList<Movie> adventure = new ArrayList<Movie>();
    private static ArrayList<Movie> animation = new ArrayList<Movie>();
    private static ArrayList<Movie> comedicMovie = new ArrayList<Movie>();
    private static ArrayList<Movie> crime = new ArrayList<Movie>();
    private static ArrayList<Movie> documentary = new ArrayList<Movie>();
    private static ArrayList<Movie> drama = new ArrayList<Movie>();
    private static ArrayList<Movie> family = new ArrayList<Movie>();
    private static ArrayList<Movie> fantasy = new ArrayList<Movie>();
    private static ArrayList<Movie> foreignMovie = new ArrayList<Movie>();
    private static ArrayList<Movie> historicalMovie = new ArrayList<Movie>();
    private static ArrayList<Movie> horror = new ArrayList<Movie>();
    private static ArrayList<Movie> musicalMovie = new ArrayList<Movie>();
    private static ArrayList<Movie> mystery = new ArrayList<Movie>();
    private static ArrayList<Movie> romance = new ArrayList<Movie>();
    private static ArrayList<Movie> scifi = new ArrayList<Movie>();
    private static ArrayList<Movie> tvMovie = new ArrayList<Movie>();
    private static ArrayList<Movie> thriller = new ArrayList<Movie>();
    private static ArrayList<Movie> war = new ArrayList<Movie>();
    private static ArrayList<Movie> western = new ArrayList<Movie>();
    private static ArrayList<Movie> topRating = new ArrayList<Movie>();
    private static ArrayList<Movie> topRevenue = new ArrayList<Movie>();

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie object to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter an actor: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        if (actors.isEmpty()) {
            String allActors = "";
            for (Movie m : movies) {
                allActors += m.getCast() + "|";
            }
            String [] ActorsList = allActors.split("\\|");
            for (String actor : ActorsList) {
                if (!actors.contains(actor)) {
                    actors.add(actor);
                }
            }
        }

        ArrayList<String> results = new ArrayList<String>();
        for (String actor : actors) {
            if (actor.toLowerCase().contains(searchTerm)) {
                results.add(actor);
            }
        }
        Collections.sort(results);
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which cast member would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedActor = results.get(choice - 1);

        ArrayList<Movie> movieResults = new ArrayList<Movie>();
        for (Movie movie : movies) {
            if (movie.getCast().contains(selectedActor)) {
                movieResults.add(movie);
            }
        }
        sortResults(movieResults);

        for (int i = 0; i < movieResults.size(); i++)
        {
            String title = movieResults.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movieResults.get(choice2 - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }




    private void searchKeywords()
    {
        System.out.print("Enter a keyword: ");
        String keyword = scanner.nextLine();

        // prevent case sensitivity
       keyword = keyword.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getKeywords();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(keyword) != -1)
            {
                //add the Movie object to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres() {
        if (genres.isEmpty()) {
            String allGenres = "";
            for (Movie movie : movies) {
                allGenres += movie.getGenres() + "|";
            }
            String[] genresList = allGenres.split("\\|");
            for (String genre : genresList) {
                if (!genres.contains(genre)) {
                    genres.add(genre);
                }
            }
            Collections.sort(genres);
        }
        for (int i = 0; i < genres.size(); i++) {
            String title = genres.get(i);
            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which genre would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Movie> movieGenre = new ArrayList<Movie>();
        if (action.isEmpty()){
            sortMovieGenres();
        }
        if (choice == 1) {
            movieGenre = action;
        }
        else if (choice == 2) {
            movieGenre = adventure;
        }
        else if (choice == 3){
            movieGenre = animation;
        }
        else if (choice == 4) {
            movieGenre = comedicMovie;
        }
        else if (choice == 5) {
            movieGenre = crime;
        }
        else if (choice == 6) {
            movieGenre = documentary;
        }
        else if (choice == 7) {
            movieGenre = drama;
        }
        else if (choice == 8) {
            movieGenre = family;
        }
        else if (choice == 9) {
            movieGenre = fantasy;
        }
        else if (choice == 10) {
            movieGenre = foreignMovie;
        }
        else if (choice == 11) {
            movieGenre = historicalMovie;
        }
        else if (choice == 12) {
            movieGenre = horror;
        }
        else if (choice == 13) {
            movieGenre = musicalMovie;
        }
        else if (choice == 14) {
            movieGenre = mystery;
        }
        else if (choice == 15) {
            movieGenre = romance;
        }
        else if (choice == 16) {
            movieGenre = scifi;
        }
        else if (choice == 17) {
            movieGenre = tvMovie;
        }
        else if (choice == 18) {
            movieGenre = thriller;
        }
        else if (choice == 19) {
            movieGenre = war;
        }
        else if (choice == 20) {
            movieGenre = western;
        }
        sortResults(movieGenre);
        for (int i = 0; i < movieGenre.size(); i++){
            String title = movieGenre.get(i).getTitle();
            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int secondChoice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movieGenre.get(secondChoice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }


    private void listHighestRated()
    {
        if (topRating.isEmpty()) {
            topRating.add(movies.get(0));
            for (int i = 1; i < movies.size(); i++) {
                if (topRating.size() < 50 || (topRating.size() == 50 && movies.get(i).getUserRating() > topRating.get(49).getUserRating())) {
                    boolean listed = false;
                    while (!listed) {
                        for (int j = 0; j < topRating.size(); j++) {
                            if (movies.get(i).getUserRating() >= topRating.get(j).getUserRating()) {
                                topRating.add(j, movies.get(i));
                                listed = true;
                                if (topRating.size() > 50){
                                    topRating.remove(50);
                                }
                                break;
                            }
                        }
                        if (!listed) {
                            topRating.add(movies.get(i));
                            listed = true;
                            if (topRating.size() > 50){
                                topRating.remove(50);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < topRating.size(); i++)
        {
            String title = topRating.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ": " + topRating.get(choiceNum - 1).getUserRating());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int secondChoice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = topRating.get(secondChoice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue()
    {
        if (topRevenue.isEmpty()) {
            topRevenue.add(movies.get(0));
            for (int i = 1; i < movies.size(); i++) {
                if (topRevenue.size() < 50 || (topRevenue.size() == 50 && movies.get(50).getRevenue() > topRevenue.get(49).getRevenue())) {
                    boolean listed = false;
                    while (!listed) {
                        for (int j = 0; j < topRevenue.size(); j++) {
                            if (movies.get(i).getRevenue() >= topRevenue.get(j).getRevenue()) {
                                topRevenue.add(j, movies.get(i));
                                listed = true;
                                break;
                            }
                        }
                        if (!listed) {
                            topRevenue.add(movies.get(i));
                            listed = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < topRevenue.size(); i++)
        {
            String title = topRevenue.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ": " + topRevenue.get(choiceNum - 1).getRevenue());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int secondChoice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = topRevenue.get(secondChoice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }



    private void sortMovieGenres() {
        for (Movie movie : movies) {
            if (movie.getGenres().contains("Action")) {
                action.add(movie);
            }
            if (movie.getGenres().contains("Adventure")) {
                adventure.add(movie);
            }
            if (movie.getGenres().contains("Animation")) {
                animation.add(movie);
            }
            if (movie.getGenres().contains("Comedy")) {
                comedicMovie.add(movie);
            }
            if (movie.getGenres().contains("Crime")) {
                crime.add(movie);
            }
            if (movie.getGenres().contains("Documentary")) {
                documentary.add(movie);
            }
            if (movie.getGenres().contains("Drama")) {
                drama.add(movie);
            }
            if (movie.getGenres().contains("Family")) {
                family.add(movie);
            }
            if (movie.getGenres().contains("Fantasy")) {
                fantasy.add(movie);
            }
            if (movie.getGenres().contains("Foreign")) {
                foreignMovie.add(movie);
            }
            if (movie.getGenres().contains("History")) {
                historicalMovie.add(movie);
            }
            if (movie.getGenres().contains("Horror")) {
                horror.add(movie);
            }
            if (movie.getGenres().contains("Music")) {
                musicalMovie.add(movie);
            }
            if (movie.getGenres().contains("Mystery")) {
                mystery.add(movie);
            }
            if (movie.getGenres().contains("Romance")) {
                romance.add(movie);
            }
            if (movie.getGenres().contains("Science Fiction")) {
                scifi.add(movie);
            }
            if (movie.getGenres().contains("TV Movie")) {
                tvMovie.add(movie);
            }
            if (movie.getGenres().contains("Thriller")) {
                thriller.add(movie);
            }
            if (movie.getGenres().contains("War")) {
                war.add(movie);
            }
            if (movie.getGenres().contains("Western")) {
                western.add(movie);
            }
        }
    }
    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}