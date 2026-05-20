INSERT INTO Specimen (id, name, region, danger_level, is_friendly) VALUES
('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', 'Korok', 'Lost Woods', 1, TRUE),
('b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e', 'Goron Elder', 'Death Mountain', 2, TRUE),
('c3d4e5f6-a7b8-9c0d-1e2f-3a4b5c6d7e8f', 'Zora Prince', 'Zoras Domain', 3, TRUE),
('d4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f9a', 'Rito Warrior', 'Hebra', 4, TRUE),
('e5f6a7b8-c9d0-1e2f-3a4b-5c6d7e8f9a0b', 'Red Bokoblin', 'Hyrule Field', 2, FALSE),
('f6a7b8c9-d0e1-2f3a-4b5c-6d7e8f9a0b1c', 'Lizalfos', 'Lanayru Wetlands', 4, FALSE),
('7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d', 'Guardian Stalker', 'Central Hyrule', 8, FALSE),
('8b9c0d1e-2f3a-4b5c-6d7e-8f9a0b1c2d3e', 'Lynel', 'Hebra Mountains', 9, FALSE),
('9c0d1e2f-3a4b-5c6d-7e8f-9a0b1c2d3e4f', 'Calamity Ganon', 'Hyrule Castle', 10, FALSE)
ON CONFLICT (id) DO NOTHING;