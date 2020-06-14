public class APIResponseParser {
    
     /**
	 * Parses the input text and returns a Book instance containing
	 * the parsed data. 
	 * @param response text to be parsed
	 * @return Book instance containing parsed data
	 */
     public static Book parse(String response) {
        Book book = new Book();
		String endRule = "<";
		
		String startRule = "<title>";		
		String title = parse(response, startRule, endRule); 
	    book.setTitle(title);
	    
	    startRule = "<name>";		
		String author = parse(response, startRule, endRule); 
	    book.setAuthor(author);
	    
	    startRule = "<original_publication_year type=\"integer\">";		
		String publicationYear = parse(response, startRule, endRule);
		if(publicationYear.equalsIgnoreCase("")) publicationYear="0";
	    book.setPublicationYear(Integer.valueOf(publicationYear));
	    
	    startRule = "<average_rating>";		
		String averageRating = parse(response, startRule, endRule);
		if(averageRating.equalsIgnoreCase("")) averageRating="0";
	    book.setAverageRating(Double.valueOf(averageRating));
	    
	    startRule = "<ratings_count type=\"integer\">";		
		String ratingsCount = parse(response, startRule, endRule);
		ratingsCount=ratingsCount.replaceAll(",","");
		if(ratingsCount.equalsIgnoreCase("")) ratingsCount="0";
	    book.setRatingsCount(Integer.valueOf(ratingsCount));
	    
	    startRule = "<image_url>";		
		String imageUrl = parse(response, startRule, endRule); 
	    book.setImageUrl(imageUrl);
	    
		
		// Your code
		return book;
     }
     
     // write overloaded parse method with the 3 parameters response, startRule, endRule
     public static String parse(String response, String startRule, String endRule){
         String result="";
         int i = response.indexOf(startRule);
         if(i>-1){
             i+=startRule.length();
             String str;
             for(; i<response.length(); ++i){
                 str=response.substring(i,i+1);
                 if(str.equalsIgnoreCase(endRule)) break;
                 result= result+str;
             }
             return result;
         }
         else return result;
     }
     
     public static void main(String[] args) {
		String response = "<work>" + 
	                            "<id type=\"integer\">2361393</id>" +
	                            "<books_count type=\"integer\">813</books_count>" +
	                            "<ratings_count type=\"integer\">1,16,315</ratings_count>" + 
	                            "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
	                            "<original_publication_year type=\"integer\">1854</original_publication_year>" +
								"<original_publication_month type=\"integer\" nil=\"true\"/>" +
								"<original_publication_day type=\"integer\" nil=\"true\"/>" +
								"<average_rating>3.79</average_rating>" +
								"<best_book type=\"Book\">" +
									"<id type=\"integer\">16902</id>" +
									"<title>Walden</title>" + 
									"<author>" +
										"<id type=\"integer\">10264</id>" + 
										"<name>Henry David Thoreau</name>" + 
									"</author>" +
									"<image_url>" + 
										"http://images.gr-assets.com/books/1465675526m/16902.jpg" +
									"</image_url>" +
									"<small_image_url>" + 
										"http://images.gr-assets.com/books/1465675526s/16902.jpg" +
									"</small_image_url>" +
								"</best_book>" +
							"</work>";
		
		APIResponseParser.parse(response);
	}
}