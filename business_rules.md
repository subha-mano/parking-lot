# 📜 Gilded Rose Business Rules

The **Gilded Rose** is a kata that involves updating the quality and sell-in value of various items based on business rules.

---

## Core Business Rules 🧠

- Each item has:
    - **Sell-in**: The number of days left to sell the item.
    - **Quality**: The value representing item quality (0 to 50, except **Sulfuras** which is always **80**).

- Daily Changes:
    - At the end of each day, **Sell-in** decreases by **1**.
    - **Quality** decreases by **1** (except for special items).

---

## Special Item Rules 🔥

| Item Name                  | Behavior                        | Expired Behavior                | Max Quality |
|------------------------------|---------------------------------|----------------------------------|-------------|
| Normal Item                 | Quality degrades by **1/day**   | Degrades by **2/day**           | 50          |
| Aged Brie                   | Quality **increases by 1/day**   | Increases **by 2/day**          | 50          |
| Sulfuras, Hand of Ragnaros   | **Legendary** item, never changes | No effect                       | **80**      |
| Backstage Passes            | Increases:<br>**+1** (>10 days)<br>**+2** (6-10 days)<br>**+3** (1-5 days) | **Drops to 0**                  | 50          |
| Conjured Items (Optional)    | Degrades **twice as fast**       | Degrades **4/day**              | 50          |

---

## Quality Limits 🚫

- Quality **never** goes below **0**.
- Quality **never** goes above **50** (except for **Sulfuras**, which is always **80**).

---

## Summary of Rules 🔑

| Item           | Daily Change         | After Expiry  | Max Quality |
|---------------|---------------------|---------------|-------------|
| Normal Item   | -1                 | -2           | 50          |
| Aged Brie     | +1                 | +2           | 50          |
| Sulfuras      | No Change          | No Change    | 80          |
| Backstage Passes | +1, +2, +3      | **Drops to 0** | 50          |
| Conjured      | -2                 | -4           | 50          |

---

### Optional Rule
| Item       | Rule                    |
|-----------|--------------------------|
| Conjured  | Degrades **twice as fast** as normal items |

--- 
