CREATE TABLE `students` (
  id int(11) NOT NULL,
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO `students` (`id`, `name`, `email`, `phone`) VALUES
(0, 'Jan Kowalski', 'kow@gmail.com', '123456789'),
(1, 'Andrzej Nowak', 'now@gmail.com', '123456788'),
(2, 'Tomasz Adamski', 'ada@gmail.com', '123456787');


CREATE USER 'szymon'@'%%' IDENTIFIED BY 'szymon';
GRANT ALL PRIVILEGES ON * . * TO 'szymon'@'%%';
FLUSH PRIVILEGES;


ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;