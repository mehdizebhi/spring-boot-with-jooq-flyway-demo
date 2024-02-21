CREATE TABLE user_preferences
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    theme      VARCHAR(255),
    language   VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users
(
    id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    email          VARCHAR(255) NOT NULL,
    password       VARCHAR(255) NOT NULL,
    preferences_id INT REFERENCES user_preferences (id),
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT user_email_unique UNIQUE (email)
);

CREATE TABLE bookmarks
(
    id         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    url        VARCHAR(1024) NOT NULL,
    title      VARCHAR(1024),
    created_by INT           NOT NULL REFERENCES users (id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tags
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT tag_name_unique UNIQUE (name)
);

CREATE TABLE bookmark_tag
(
    bookmark_id INT NOT NULL REFERENCES bookmarks (id),
    tag_id      INT NOT NULL REFERENCES tags (id)
);