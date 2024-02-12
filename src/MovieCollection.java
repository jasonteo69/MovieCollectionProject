import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

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
        System.out.print("Enter a tital search term: ");
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
        System.out.print("Enter cast member: ");
        String castMember = scanner.nextLine();

        //prevent case sensitivity
        castMember = castMember.toLowerCase();

        ArrayList<String> arrList = new ArrayList<String>();
        for (int i = 0; i < movies.size(); i++) {
            String str = movies.get(i).getCast();
            String[] arr = str.split("\\|");
            for (String each : arr) {
                String inLower = each.toLowerCase();
                if (!arrList.contains(each) && inLower.contains(castMember)) {
                    arrList.add(each);
                }
            }
        }
        sortResult(arrList);
        int idx = 0;
        for (String cast : arrList) {
            int choiceNum = idx + 1;
            System.out.println("" + choiceNum + ". " + cast);
            idx++;
        }
        System.out.println("Which actor would you like to learn more about");
        System.out.print("Enter actor: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedActor = arrList.get(choice - 1);

        ArrayList<Movie> movies1 = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getCast().contains(selectedActor)) {
                movies1.add(movies.get(i));
            }
        }
        sortResults(movies1);
        for (int k = 0; k < movies1.size(); k++) {
            int track = k + 1;
            System.out.println("" + track + ". " + movies1.get(k).getTitle());
        }
        System.out.print("Choose a movie: ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = movies1.get(choice2 - 1);
        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keywords = movies.get(i).getKeywords();
            keywords = keywords.toLowerCase();

            if (keywords.indexOf(searchTerm) != -1)
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
            String keyword = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + keyword);
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

    private void listGenres()
    {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < movies.size(); i++) {
            String str = movies.get(i).getGenres();
            String[] arr = str.split("\\|");
            for (String each : arr) {
                if (!list.contains(each)) {
                    list.add(each);
                }
            }
        }
        sortResult(list);
        int idx = 0;
        for (String genre : list) {
            int choiceNum = idx + 1;
            System.out.println("" + choiceNum + ". " + genre);
            idx++;
        }
        System.out.print("Choose a genre: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedGenre = list.get(choice - 1);

        ArrayList<Movie> movies1 = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getGenres().contains(selectedGenre)) {
                movies1.add(movies.get(i));
            }
        }
        sortResults(movies1);
        for (int k = 0; k < movies1.size(); k++) {
            int track = k + 1;
            System.out.println("" + track + ". " + movies1.get(k).getTitle());
        }
        System.out.print("Choose a movie: ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = movies1.get(choice2 - 1);
        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated() {
        double max = 0.0;
        Double[] arr = new Double[movies.size()];

        for (int i = 0; i < movies.size(); i++) {
            arr[i] = movies.get(i).getUserRating();
        }

        Arrays.sort(arr);
        ArrayList<String> titles = new ArrayList<String>();
        double[] sortedArr = new double[50];

        int idx = arr.length - 51;
        for (int i = 0; i < sortedArr.length; i++) {
            sortedArr[i] = arr[idx];
            idx++;
        }

        for (int i = 0; i < movies.size(); i++) {
            double rating = movies.get(i).getUserRating();
            //if ratings match, find the title
            for (int k = 49; k > 0; k--) {
                if (rating == sortedArr[k]) {
                    titles.add(movies.get(i).getTitle());
                    break;
                }
            }
        }
        int num = 0;
        int here = 49;
        for (int i = 0; i < sortedArr.length; i++) {
            num = i + 1;
            String movieName = titles.get(here);
            System.out.println("" + num + ". " + movieName + ": "  + sortedArr[here]);
            here--;
        }
    }
    private void listHighestRevenue()
    {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        int[] rev = new int[movies.size()];
        for (int i = 0; i < movies.size(); i++) {
            int revenue = movies.get(i).getRevenue();
            String title = movies.get(i).getTitle();
            rev[i] = revenue;
            map.put(revenue, title);
        }

        Arrays.sort(rev);
        int[] highest = new int[50];
        int idx = 0;
        for (int i = rev.length - 1; i >= rev.length - 50; i--) {
            highest[idx] = rev[i];
            idx++;
        }

        int num = 0;
        for (int i = 0; i < highest.length; i++) {
            num = i + 1;
            String title = map.get(highest[i]);
            System.out.println("" + num + ". " + title + ": "  + highest[i]);
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
    private void sortResult(ArrayList<String> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            String tempTitle = listToSort.get(j);

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1)) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, tempTitle);
        }
    }
}