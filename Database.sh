podman pull postgres

podman volume create isc-postgres-data

podman run -d --name isc-postgres -e POSTGRES_DB=ISC-DB -e POSTGRES_USER=ISC-ADMIN -e POSTGRES_PASSWORD=ISC-PASSWORD -e PGDATA=/var/lib/postgresql/data/pgdata -p 5432:5432 -v isc-postgres-data:/var/lib/postgresql/data postgres

podman ps

podman exec -it isc-postgres psql -U ISC-ADMIN ISC-DB