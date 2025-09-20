-- nutrition에 price 컬럼 추가 (원 단위, 일단 NULL 허용)
ALTER TABLE nutrition ADD COLUMN price INTEGER;

-- 2) 기존 menu.price 값을 nutrition.price로 복사
--    nutrition이 여러 행이면 같은 가격을 모두에 복사 (이전 스키마 한 개 가격 공유 가정)
UPDATE nutrition n
SET price = m.price
    FROM menu m
WHERE n.menu_id = m.id
  AND (n.price IS NULL OR n.price = 0);

-- menu.price 컬럼 제거
ALTER TABLE menu DROP COLUMN price;
