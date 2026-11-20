INSERT INTO genre (id, name, description) VALUES (1, 'Drame', 'Histoires dramatiques')
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, description = EXCLUDED.description;
INSERT INTO genre (id, name, description) VALUES (2, 'Science-fiction', 'Histoires futuristes ou scientifiques')
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, description = EXCLUDED.description;
INSERT INTO genre (id, name, description) VALUES (3, 'Comédie', 'Histoires humoristiques')
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, description = EXCLUDED.description;