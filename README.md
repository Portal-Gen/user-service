# User Profile Service

The User Profile Service is a microservice that handles user profile management and user preferences for a travel application. It provides APIs for creating, retrieving, updating, and deleting user profiles, as well as managing user preferences for different place types.

## Prerequisites

Before running the User Profile Service, ensure that you have the following installed:

- Docker
- Docker Compose

## Getting Started

To get started with the User Profile Service, follow these steps:

1. Clone the repository:

   ```
   git clone https://github.com/your-username/user-profile-service.git
   ```

2. Navigate to the project directory:

   ```
   cd user-profile-service
   ```

3. Build and run the application using Docker Compose:

   ```
   docker-compose up --build
   ```

   This command will build the Docker images for the User Profile Service, database, and Nginx reverse proxy, and start the containers.

4. Wait for the application to start. You should see logs indicating that the application is running and connected to the database.

## API Endpoints

The User Profile Service exposes the following API endpoints:

### User Profile

- `POST /api/v1/user-profile`: Create a new user profile.
- `GET /api/v1/user-profile/{userProfileId}`: Retrieve a user profile by ID.
- `PUT /api/v1/user-profile`: Update a user profile.
- `DELETE /api/v1/user-profile/{userProfileId}`: Delete a user profile by ID.

### Place Preference

- `POST /api/v1/place-preference/{userProfileId}/default`: Set default place preferences for a user profile.
- `GET /api/v1/place-preference/{userProfileId}`: Retrieve place preferences for a user profile.
- `PUT /api/v1/place-preference`: Update place preferences for a user profile.
- `GET /api/v1/place-preference/{userProfileId}/scores`: Calculate place preference scores for a user profile.

## Interacting with the Application

To interact with the User Profile Service, you can use any HTTP client such as cURL or Postman. Here are a few examples:

### Create a User Profile

```
POST http://localhost/api/v1/user-profile
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

### Retrieve a User Profile

```
GET http://localhost/api/v1/user-profile/1
```

### Update a User Profile

```
PUT http://localhost/api/v1/user-profile
Content-Type: application/json

{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "bio": "Updated bio"
}
```

### Delete a User Profile

```
DELETE http://localhost/api/v1/user-profile/1
```

### Set Default Place Preferences

```
POST http://localhost/api/v1/place-preference/1/default
```

### Retrieve Place Preferences

```
GET http://localhost/api/v1/place-preference/1
```

### Update Place Preferences

```
PUT http://localhost/api/v1/place-preference
Content-Type: application/json

{
  "userProfileId": 1,
  "preferences": {
    "BEACH": 0.8,
    "MOUNTAIN": 0.6,
    "CITY": 0.4
  }
}
```

### Calculate Place Preference Scores

```
GET http://localhost/api/v1/place-preference/1/scores
```

## Configuration

The User Profile Service uses environment variables for configuration. The following environment variables can be set:

- `DB_HOST`: Database host (default: `db`)
- `DB_PORT`: Database port (default: `3306`)
- `DB_NAME`: Database name (default: `portalgen_user`)
- `DB_USER`: Database username (default: `mysql`)
- `DB_PASSWORD`: Database password (default: `mysql`)

These environment variables can be modified in the `docker-compose.yml` file.

## Contributing

Contributions to the User Profile Service are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request in the repository.

## License

The User Profile Service is open-source software licensed under the [MIT License](https://opensource.org/licenses/MIT).

## Contact

For any inquiries or questions, please contact the project maintainer at [your-email@example.com](mailto:your-email@example.com).
