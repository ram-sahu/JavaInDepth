import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
	
	private static void hashSetDemo() {
		Set<String> set1 = new HashSet<>();
		set1.add("a");
		set1.add("b");
		set1.add("a");
						
		System.out.println("set1: " +  set1);
		
		Book book1 = new Book("Walden", "Henry Thoreau", 1854);
		Book book2 = new Book("Walden", "Henry Thoreau", 1854);
		Set<Book> set2 = new HashSet<>();
		set2.add(book1);
		set2.add(book2);
		System.out.println("set2: " +  set2);
	}
	
	public static Set<Book> treeSetDemo(Comparator comparator) {
        if(comparator== null) comparator= Comparator.naturalOrder();

		Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
		Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
		Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);
			  
		Set<Book> books = new TreeSet<>(comparator);
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
		
	      
	    for (Book book : books) {
	    	System.out.println(book);
	    }		
	    
	    return books;
	}
	
	public static void main(String[] args) {
		//hashSetDemo();
        treeSetDemo(null);
        System.out.println("----------a");
        treeSetDemo(new PubDateAscComparator());
        System.out.println("-------------d");
        treeSetDemo(new PubDateDescComparator());
	}	
	
}

public class Book implements Comparable {
	private String title;
	private String author;
	private int year;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	/*public int hashCode() {
		return title.hashCode();
	}

	public boolean equals(Object o) {
		return (year == (((Book)o).getYear())) && (author.equals((((Book)o).getAuthor())));
	}*/	
	
	public int compareTo(Object book) {
		 return getTitle().compareTo(((Book)book).getTitle()); // utilizing String’s compareTo
	}
	
}

class PubDateDescComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
        
        if( ((Book)o2).getYear()!=((Book)o1).getYear() )
                return ((Book)o2).getYear() - ((Book)o1).getYear();
        else
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
	}
	
}

class PubDateAscComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
        
        if( ((Book)o1).getYear()!=((Book)o2).getYear() )
                return ((Book)o1).getYear() - ((Book)o2).getYear();
        else
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
	}
	
}
