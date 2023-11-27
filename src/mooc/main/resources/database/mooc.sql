CREATE TABLE IF NOT EXISTS courses (
	id CHAR(36) NOT NULL,
	name VARCHAR(255) NOT NULL,
	duration VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS courses_counter (
	id CHAR(36) NOT NULL,
	total INT NOT NULL,
	existing_courses JSON NOT NULL,
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci;
INSERT IGNORE INTO courses_counter (id, total, existing_courses)
VALUES ('efbaff16-8fcd-4689-9fc9-ec545d641c46', 0, '[]');

CREATE TABLE IF NOT EXISTS steps (
	id CHAR(36) NOT NULL,
	title VARCHAR(155) NOT NULL,
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS steps_challenges (
	id CHAR(36) NOT NULL,
	statement TEXT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_steps_challenges__step_id FOREIGN KEY (id) REFERENCES steps(id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS steps_videos (
	id CHAR(36) NOT NULL,
	url VARCHAR(255) NOT NULL,
	text TEXT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_steps_video__step_id FOREIGN KEY (id) REFERENCES steps(id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS domain_events (
	id CHAR(36) NOT NULL,
	aggregate_id CHAR(36) NOT NULL,
	name VARCHAR(255) NOT NULL,
	body JSON NOT NULL,
	occurred_on TIMESTAMP NOT NULL,
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci;
