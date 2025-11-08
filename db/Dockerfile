FROM mysql:8.0

# Set root password
ENV MYSQL_ROOT_PASSWORD=college

# Copy all the database files from the db folder
COPY *.dump /docker-entrypoint-initdb.d/
COPY employees.sql /docker-entrypoint-initdb.d/