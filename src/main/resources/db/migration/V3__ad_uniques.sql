-- 브랜드명은 고유
ALTER TABLE brand
    ADD CONSTRAINT uq_brand_name UNIQUE (name);

-- 메뉴: 같은 브랜드 내 동일 이름은 1개만
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux_menu_brand_name'
  ) THEN
CREATE UNIQUE INDEX ux_menu_brand_name ON menu(brand_id, name);
END IF;
END $$;

-- 매장: 같은 브랜드 내 동일 매장명은 1개만 (개발 편의 기준)
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux_store_brand_name'
  ) THEN
CREATE UNIQUE INDEX ux_store_brand_name ON store(brand_id, name);
END IF;
END $$;

-- 영양: (menu_id, size) 조합 고유 (이미 있으면 생략)
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux_nutrition_menu_size'
  ) THEN
CREATE UNIQUE INDEX ux_nutrition_menu_size ON nutrition(menu_id, size);
END IF;
END $$;