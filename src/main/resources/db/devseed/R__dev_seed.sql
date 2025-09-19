-- =========================================
-- 브랜드 선삽입 (카페/음식점)
-- =========================================
INSERT INTO brand (name, category)
VALUES ('매머드커피','cafe')
ON CONFLICT (name) DO NOTHING;

INSERT INTO brand (name, category)
VALUES ('홍콩반점','restaurant')
ON CONFLICT (name) DO NOTHING;

-- =========================================
-- 매장 (카테고리별 핀 확인용: 카페 2, 음식점 1)
-- =========================================
-- 매머드커피: 선릉점 / 역삼점
WITH bid AS (SELECT id AS brand_id FROM brand WHERE name='매머드커피')
INSERT INTO store (brand_id, name, lat, lon, address)
SELECT brand_id, '매머드커피 선릉점', 37.5040, 127.0480, '서울 강남구 테헤란로'
FROM bid
ON CONFLICT (brand_id, name) DO NOTHING;

WITH bid AS (SELECT id AS brand_id FROM brand WHERE name='매머드커피')
INSERT INTO store (brand_id, name, lat, lon, address)
SELECT brand_id, '매머드커피 역삼점', 37.4980, 127.0350, '서울 강남구 역삼동'
FROM bid
ON CONFLICT (brand_id, name) DO NOTHING;

-- 홍콩반점: 선릉역점 (음식점 1개)
WITH bid AS (SELECT id AS brand_id FROM brand WHERE name='홍콩반점')
INSERT INTO store (brand_id, name, lat, lon, address)
SELECT brand_id, '홍콩반점 선릉역점', 37.5045, 127.0485, '서울 강남구 선릉로'
FROM bid
ON CONFLICT (brand_id, name) DO NOTHING;

-- =========================================
-- 홍콩반점 메뉴/영양 예시
-- =========================================
WITH bid AS (
    SELECT id AS brand_id FROM brand WHERE name='홍콩반점'
),
m1 AS (
INSERT INTO menu (brand_id, name, price)
SELECT brand_id, '짜장면', 6000 FROM bid
ON CONFLICT (brand_id, name) DO NOTHING
RETURNING id, name
)
SELECT 1;

WITH bid AS (
    SELECT id AS brand_id FROM brand WHERE name='홍콩반점'
),
m2 AS (
INSERT INTO menu (brand_id, name, price)
SELECT brand_id, '짬뽕', 7000 FROM bid
ON CONFLICT (brand_id, name) DO NOTHING
RETURNING id, name
)
SELECT 1;

INSERT INTO nutrition (menu_id, size, calories, protein, fat, carbohydrate, sugar, caffeine_mg)
SELECT (SELECT id FROM menu WHERE name='짜장면' AND brand_id=(SELECT id FROM brand WHERE name='홍콩반점')),
       'Regular', 750, 18, 20, 110, 10, NULL
ON CONFLICT (menu_id, size) DO NOTHING;

INSERT INTO nutrition (menu_id, size, calories, protein, fat, carbohydrate, sugar, caffeine_mg)
SELECT (SELECT id FROM menu WHERE name='짬뽕' AND brand_id=(SELECT id FROM brand WHERE name='홍콩반점')),
       'Regular', 800, 22, 17, 105, 8, NULL
ON CONFLICT (menu_id, size) DO NOTHING;

-- =========================================
-- (옵션) 매머드커피 메뉴/영양 예시 – 기존 유지
-- =========================================
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

INSERT INTO nutrition (menu_id, size, calories, protein, fat, carbohydrate, sugar, caffeine_mg)
SELECT (SELECT id FROM menu WHERE name='콜드브루' AND brand_id=(SELECT id FROM brand WHERE name='매머드커피')),
       'Regular', 10, 0.3, 0, 2, 0, 100
ON CONFLICT (menu_id, size) DO NOTHING;

INSERT INTO nutrition (menu_id, size, calories, protein, fat, carbohydrate, sugar, caffeine_mg)
SELECT (SELECT id FROM menu WHERE name='디카페인 아메리카노' AND brand_id=(SELECT id FROM brand WHERE name='매머드커피')),
       'Regular', 5, 0.2, 0, 1, 0, 2
ON CONFLICT (menu_id, size) DO NOTHING;