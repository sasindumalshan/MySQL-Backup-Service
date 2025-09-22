# MySQL Database Backup Service

A Spring Boot application that provides automated and on-demand backup functionality for MySQL databases. The service can create local backups and includes a placeholder for Google Drive integration for cloud storage.

## Features

- **Local Database Backups**: Create backups of MySQL databases and store them locally
- **Scheduled Backups**: Configure automatic backups using cron expressions
- **REST API**: Simple HTTP endpoints to trigger backups
- **Google Drive Integration**: Placeholder for future cloud backup functionality

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL Server 5.7 or higher
- `mysqldump` utility must be available in the system PATH

## Configuration

Update the `application.properties` file with your database credentials and backup settings:

```properties
# Database Configuration
db.host=localhost
db.port=3306
db.user=your_username
db.pw=your_password
db.db-name=your_database_name
db.backupPath=src/main/resources/db_backup/mysql/

# Scheduled Backup (cron expression)
backup.schedule.cron=0 0 2 * * ?  # Runs at 2 AM every day
```

## API Endpoints

### Create a Local Backup

```
POST /api/db/backup/local
```

**Response**
- Success: `200 OK` with the path to the backup file
- Error: `422 Unprocessable Entity` if backup fails

## Scheduled Backups

The application is configured to run scheduled backups based on the cron expression in `application.properties`. By default, it's set to run daily at 2 AM.

## Google Drive Integration (Planned)

This feature is currently a placeholder and not implemented. Future development will include the ability to automatically upload backups to Google Drive.

## Build and Run

1. Clone the repository
2. Configure the `application.properties` file
3. Build the application:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   java -jar target/database-backup-service-0.0.1-SNAPSHOT.jar
   ```

## Logging

Application logs are output to the console. The backup process logs the location of created backup files and any errors that occur during the backup process.

## License

This project is licensed under the terms of the MIT license. See the [LICENSE](LICENSE) file for details.

## Author

Sasindu Malshan

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.