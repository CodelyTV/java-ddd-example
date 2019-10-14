CREATE TABLE `courses` (
  `id` CHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `duration` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `steps` (
    `id` CHAR(36) NOT NULL,
    `title` VARCHAR(155) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `steps_challenges` (
    `id` CHAR(36) NOT NULL,
    `statement` TEXT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_steps_challenges__step_id` FOREIGN KEY (`id`) REFERENCES `steps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `steps_videos` (
    `id` CHAR(36) NOT NULL,
    `url` VARCHAR(255) NOT NULL,
    `text` TEXT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_steps_video__step_id` FOREIGN KEY (`id`) REFERENCES `steps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
