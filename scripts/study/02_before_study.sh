#!/bin/bash
set -e

clear

echo ""
echo "================================================== "
echo "⚠️ 이 스크립트는 스터디 시작 전, 로컬 main 브랜치를"
echo "  upstream/main 최신 상태로 동기화합니다."
echo ""
echo "  현재 브랜치에서 작업 중인 내용이 있다면"
echo "  반드시 먼저 commit 또는 stash 하세요."
echo "================================================== "
echo ""

read -p "👉 최신화를 진행하시겠습니까? (Y/N): " CONFIRM
echo ""

if [[ ! "$CONFIRM" =~ ^[Yy]$ ]]; then
  echo "❌ 사용자가 취소했습니다."
  echo ""
  exit 1
fi

# upstream 존재 여부 확인
if ! git remote | grep -q "^upstream$"; then
  echo "❌ upstream remote가 설정되어 있지 않습니다."
  echo "   초기 세팅 스크립트를 먼저 실행하세요."
  echo ""
  exit 1
fi

echo "👉 upstream fetch"
git fetch upstream
echo ""

echo "👉 main으로 이동"
git checkout main
echo ""

echo "👉 upstream/main 병합"
if git merge upstream/main; then
  echo ""
  echo "✅ 최신 상태로 동기화 완료"
else
  echo ""
  echo "❌ 병합 중 충돌이 발생했습니다."
  echo "   충돌 해결 후 다시 시도하세요."
  echo ""
  exit 1
fi

echo ""
