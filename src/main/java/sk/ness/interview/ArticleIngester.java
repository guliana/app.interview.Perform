package sk.ness.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import sk.ness.interview.config.DatabaseConfig;
import sk.ness.interview.service.ArticleService;

/**
 * Application runner that initializes necessary parts of the system.
 *
 * @author michal.kmetka
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "sk.ness.interview", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ApplicationRunner.class) })
@Import(DatabaseConfig.class)
public class ArticleIngester {

	public static void main(final String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				ArticleIngester.class)) {
			context.registerShutdownHook();

			final ArticleService articleService = context.getBean(ArticleService.class);

			// Load file with articles and ingest

			File input = new File("articles_to_ingest.txt");
			try (Scanner sc = new Scanner(input)) {
				articleService.ingestArticles(sc.useDelimiter("\\A").next());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}
