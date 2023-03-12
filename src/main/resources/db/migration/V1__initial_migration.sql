CREATE TABLE `course` (
    `id` INT NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `name` VARCHAR(64) NOT NULL UNIQUE,
    `course_id` VARCHAR(64) NOT NULL UNIQUE,
    `units` INT NOT NULL,
    `teacher_id` VARCHAR(64),
    `student_id` VARCHAR(64),

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `student` (
    `id` INT NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `first_name` VARCHAR(64) NOT NULL,
    `last_name` VARCHAR(64) NOT NULL,
    `email` VARCHAR(64) NOT NULL,
    `phone_number` VARCHAR(64) NOT NULL,
    `student_id` VARCHAR(64),

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `teacher` (
    `id` INT NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `first_name` VARCHAR(64) NOT NULL,
    `last_name` VARCHAR(64) NOT NULL,
    `email` VARCHAR(64) NOT NULL,
    `phone_number` VARCHAR(64) NOT NULL,
    `teacher_id` VARCHAR(64),

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `student_grades` (
    `id` INT NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `student_id` VARCHAR(64) NOT NULL,
    `course_id` VARCHAR(64) NOT NULL,
    `score` DECIMAL(3,2) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
