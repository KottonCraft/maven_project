// toast.js

/**
 * 通用 Toast 提示工具
 * @param {string} message - 显示的消息内容
 * @param {object} [options] - 配置选项
 * @param {'success'|'danger'|'warning'|'info'} [options.type='success'] - 提示类型
 * @param {number} [options.duration=2000] - 显示时长(毫秒)
 * @param {HTMLElement} [options.container=document.body] - 挂载容器
 * @param {'fixed'|'absolute'} [options.positionType='fixed'] - 定位类型
 */
export function showToast(message, options = {}) {
  // 合并默认配置
  const config = {
    type: 'success',
    duration: 2000,
    container: document.body,
    positionType: 'fixed',
    ...options
  };

  // 创建 Toast 元素
  const toastEl = document.createElement('div');
  toastEl.className = `toast toast-${config.type}`;
  toastEl.innerHTML = `
    <div class="toast-body d-flex justify-content-between align-items-center">
      <span>${message}</span>
      <button type="button" class="btn-close btn-close-white" data-bs-dismiss="toast"></button>
    </div>
  `;

  // 创建容器（如果不存在）
  const containerId = 'toast-container-' + config.positionType;
  let toastContainer = config.container.querySelector(`#${containerId}`);

  if (!toastContainer) {
    toastContainer = document.createElement('div');
    toastContainer.id = containerId;
    toastContainer.className = `toast-container toast-${config.positionType}`;
    config.container.appendChild(toastContainer);
  }

  // 添加定位样式
  toastContainer.style.position = config.positionType;

  // 添加元素到容器
  toastContainer.appendChild(toastEl);

  // 初始化 Bootstrap Toast
  const bsToast = new bootstrap.Toast(toastEl, {
    autohide: true,
    delay: config.duration
  });

  bsToast.show();

  // 自动清理
  toastEl.addEventListener('hidden.bs.toast', () => {
    toastEl.remove();
    if (toastContainer.children.length === 0) {
      toastContainer.remove();
    }
  });
}