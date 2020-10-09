INSERT INTO notes (title, content, note_id, version, actual_version, deleted, created, modified)
VALUES  ('note1', 'this is content 1', '03fa73f4-cdb5-479a-9a47-d146179b04bc', 1, false , false, '2020-09-12T12:30', '2020-09-12T15:31'),
        ('note1', 'this is content 2', '03fa73f4-cdb5-479a-9a47-d146179b04bc', 2, false , false, '2020-09-12T13:30', '2020-09-12T17:30'),
        ('note1', 'this is content 3', '03fa73f4-cdb5-479a-9a47-d146179b04bc', 3, false , false, '2020-09-12T14:30', '2020-09-12T18:30'),
        ('note1', 'this is content 4', '03fa73f4-cdb5-479a-9a47-d146179b04bc', 4, true, false, '2020-09-12T18:30', '2020-09-12T22:30'),
        ('note2', 'this is content222', '8d15a0c1-9a1c-4046-b31b-2206971bc567', 1, true, false, '2017-09-12T12:30', '2026-09-12T15:30'),
        ('note3', 'mock content', '55fa74f4-cdb5-479a-9a47-d146179b24bh', 1, true, false, '2017-09-12T12:30', '2026-09-12T15:30'),
        ('note4', 'mock cont', '90963cb0-20af-4cb9-96fd-575a45a9a1b7', 1, true, false, '2017-09-12T12:30', '2026-09-12T15:30'),
        ('note5', 'mock content2', '88fa74f4-cdb5-479a-9a47-d146179b24bh', 1, false, false, '2017-09-12T12:30', '2026-09-12T15:30');