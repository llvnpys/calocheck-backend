-- 매장
WITH bid AS (SELECT id AS brand_id FROM brand WHERE name='매머드커피')
INSERT INTO store (brand_id, name, lat, lon, address)
SELECT brand_id, '매머드커피 선릉점', 37.5040, 127.0480, '서울 강남구 테헤란로' FROM bid
    ON CONFLICT (brand_id, name) DO NOTHING;

WITH bid AS (SELECT id AS brand_id FROM brand WHERE name='매머드커피')
INSERT INTO store (brand_id, name, lat, lon, address)
SELECT brand_id, '매머드커피 역삼점', 37.4980, 127.0350, '서울 강남구 역삼동' FROM bid
    ON CONFLICT (brand_id, name) DO NOTHING;

-- 브랜드
WITH b AS (
INSERT INTO brand (name, category)
VALUES ('매머드커피','cafe')
ON CONFLICT (name) DO NOTHING
    RETURNING id
    )
SELECT 1;

-- 메뉴
WITH bid AS (
    SELECT id AS brand_id FROM brand WHERE name='매머드커피'
),
     m AS (
INSERT INTO menu (brand_id, name, price)
SELECT brand_id, '콜드브루', 2500 FROM bid
    ON CONFLICT (brand_id, name) DO NOTHING
  RETURNING id, name
)
SELECT 1;

WITH bid AS (SELECT id AS brand_id FROM brand WHERE name='매머드커피'),
     m2 AS (
INSERT INTO menu (brand_id, name, price)
SELECT brand_id, '디카페인 아메리카노', 2700 FROM bid
    ON CONFLICT (brand_id, name) DO NOTHING
  RETURNING id, name
)
SELECT 1;

-- 영양(사이즈별)
INSERT INTO nutrition (menu_id, size, calories, protein, fat, carbohydrate, sugar, caffeine_mg)
SELECT (SELECT id FROM menu WHERE name='콜드브루' AND brand_id=(SELECT id FROM brand WHERE name='매머드커피')),
       'Regular', 10, 0.3, 0, 2, 0, 100
    ON CONFLICT (menu_id, size) DO NOTHING;

INSERT INTO nutrition (menu_id, size, calories, protein, fat, carbohydrate, sugar, caffeine_mg)
SELECT (SELECT id FROM menu WHERE name='디카페인 아메리카노' AND brand_id=(SELECT id FROM brand WHERE name='매머드커피')),
       'Regular', 5, 0.2, 0, 1, 0, 2
    ON CONFLICT (menu_id, size) DO NOTHING;