package lk.devil.database_backup_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatabaseBackupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseBackupServiceApplication.class, args);
	}

}
